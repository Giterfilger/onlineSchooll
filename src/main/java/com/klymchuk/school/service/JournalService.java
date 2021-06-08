package com.klymchuk.school.service;

import com.klymchuk.school.dto.*;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Journal;
import com.klymchuk.school.model.Student;
import com.klymchuk.school.model.Subject;
import com.klymchuk.school.model.WorkType;
import com.klymchuk.school.repo.JournalRepository;
import com.klymchuk.school.repo.StudentRepository;
import com.klymchuk.school.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class JournalService {

    private final JournalRepository journalRepository;

    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public MainJournalDto getById(int id) {
        return modelMapper.map(getJournalById(id), MainJournalDto.class);
    }

    public MainJournalDto save(JournalDto journalDto, int subjectId, int studentId) {
        Journal journal = modelMapper.map(journalDto, Journal.class);
        journal.setSubject(subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id: " + subjectId + "not found")));
        journal.setStudent(studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + studentId + "not found")));
        return modelMapper.map(journalRepository.save(journal), MainJournalDto.class);
    }

    public MainJournalDto update(JournalDto journalDto, int id) {
        Journal journal = getJournalById(id);
        modelMapper.map(journalDto, journal);
        return modelMapper.map(journalRepository.save(journal), MainJournalDto.class);
    }

    public void delete(int id) {
        journalRepository.delete(getJournalById(id));
    }

    public List<MainJournalDto> getAll() {
        return journalRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalByFilter(JournalFilterDto journalFilter, int studentId) {
        if (!studentRepository.findById(studentId).isPresent()) {
            throw new EntityNotFoundException("Student with id: " + studentId + " not found");
        }

        return journalRepository.findByStudentId(studentId)
                .stream()
                .filter(j -> journalFilter.getSubjectIds().contains(j.getSubject().getId()))
                .filter(j -> j.getDate().isBefore(LocalDate.parse(journalFilter.getEndDate()))
                        && j.getDate().isAfter(LocalDate.parse(journalFilter.getStartDate())))
                .filter(j -> {
                    if (journalFilter.isMark()) {
                        return j.getMark() != null &&
                                (journalFilter.getTypesOfWork().contains("усі") ||
                                        journalFilter.getTypesOfWork().contains(j.getType()));
                    } else {
                        return true;
                    }
                })
                .sorted(Comparator.comparing(Journal::getDate).reversed())
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalByTeacherFilter(JournalTeacherFilterDto journalFilter) {
        return journalRepository.findByStudentIdIn(journalFilter.getStudentIds())
                .stream()
                .filter(j -> j.getDate().isBefore(LocalDate.parse(journalFilter.getEndDate()))
                        && j.getDate().isAfter(LocalDate.parse(journalFilter.getStartDate())))
                .filter(j -> journalFilter.getSubjectIds().contains(j.getSubject().getId()))
                .sorted(Comparator.comparing(Journal::getDate).reversed())
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }


    public Map<Month, Double> getAverageMarkOfMonth(int studentId, int subjectId){
        Map<String, Double> coefficientMap = getMapCoefficient(subjectId);

        Map<Month, List<Journal>> journalsByMonthMap= journalRepository.findByStudentIdAndSubjectId(studentId, subjectId)
                .stream()
                .collect(Collectors.groupingBy(j -> j.getDate().getMonth()));

        return journalsByMonthMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> getAverageMarkWithCoefficient(coefficientMap, e.getValue())));
    }

    public Integer getStudentRatingBySubjectId(int classId, int studentId, int subjectId){
        List<Student> students = studentRepository.getStudentByClazzId(classId);

        List<Double> averageStudentMarks = students.stream()
                .map(s -> getAverageStudentSubjectMarkWithoutRound(s.getId(), subjectId))
                .sorted(Comparator.comparingDouble(Double::doubleValue).reversed())
                .collect(Collectors.toList());

        return averageStudentMarks.indexOf(getAverageStudentSubjectMarkWithoutRound(studentId, subjectId)) + 1;
    }

    public long getAverageStudentSubjectMark(int studentId, int subjectId) {
        return Math.round(getAverageStudentSubjectMarkWithoutRound(studentId, subjectId));
    }

    public Double getAverageStudentSubjectMarkWithoutRound(int studentId, int subjectId) {
        Map<String, Double> coefficientMap = getMapCoefficient(subjectId);

        if (!studentRepository.findById(studentId).isPresent()) {
            throw new EntityNotFoundException("Student with id: " + studentId + " not found");
        }

        List<Journal> journals = journalRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        return getAverageMarkWithCoefficient(coefficientMap, journals);
    }

    public List<RatingDto> getAverageMarkByClass(int clazzId, int subjectId) {
        return studentRepository.getStudentByClazzId(clazzId)
                .stream() 
                .map(s -> RatingDto.builder()
                            .id(s.getId())
                            .name(s.getName())
                            .surname((s.getSurname()))
                            .rating(getAverageStudentSubjectMarkWithoutRound(s.getId(), subjectId))
                            .build())
                .sorted(Comparator.comparingDouble(RatingDto::getRating).reversed())
                .collect(Collectors.toList());
    }

    public List<WorkTypePercentDto> getPercentOfWorkType(int subjectId, int studentId) {

        if (!studentRepository.findById(studentId).isPresent()) {
            throw new EntityNotFoundException("Student with id: " + studentId + " not found");
        }

        List<Journal> journals = journalRepository.findByStudentIdAndSubjectId(studentId, subjectId);

        Map<String, Integer> mapOfWorkType = journals.stream()
                .filter(j -> j.getMark() != null)
                .collect(Collectors.groupingBy(Journal::getType, summingInt(Journal::getMark)));

        Map<String, Long> mapWorkTypeAndCountOfMark = journals.stream()
                .filter(j -> j.getMark() != null)
                .collect(Collectors.groupingBy(Journal::getType, counting()));

        return mapOfWorkType.entrySet().stream()
                .map(m -> new WorkTypePercentDto(m.getKey(), getPercent(m.getValue(),
                        mapWorkTypeAndCountOfMark.get(m.getKey()))))
                .collect(Collectors.toList());
    }

    private Journal getJournalById(int id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("journal with id: " + id + "not found"));
    }

    private Map<String, Double> getMapCoefficient(int subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("subject with id: " + subjectId + "not found"));
        return subject.getWorkTypes().stream()
                .collect(groupingBy(WorkType::getName,
                        averagingInt(WorkType::getCoefficient)));
    }

    private double getAverageMarkWithCoefficient(Map<String, Double> coefficientMap, List<Journal> journals) {
        int allCoefficients = 0;

        double markWithCoefficients = 0;
        for (Journal journal : journals) {
            if (journal.isVisiting()) {
                markWithCoefficients += journal.getMark() * coefficientMap.get(journal.getType());
                allCoefficients += coefficientMap.get(journal.getType());
            }
        }
        if (allCoefficients == 0) {
            return 0.0;
        }
        return markWithCoefficients / allCoefficients;
    }

    private double getPercent(double value, long count) {
        log.info("value: {}", value);
        log.info("count: {}", count);
        return Math.round(((value * 100) / (count * 12)) * 100.0) / 100.0;
    }

}
