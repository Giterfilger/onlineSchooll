package com.klymchuk.school.service;

import com.klymchuk.school.dto.JournalDto;
import com.klymchuk.school.dto.JournalFilterDto;
import com.klymchuk.school.dto.MainJournalDto;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Journal;
import com.klymchuk.school.repo.JournalRepository;
import com.klymchuk.school.repo.StudentRepository;
import com.klymchuk.school.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<MainJournalDto> getJournalByFilter(JournalFilterDto journalFilter, int student_id) {
        if (!studentRepository.findById(student_id).isPresent()) {
            throw new EntityNotFoundException("Subject with id: " + student_id + " not found");
        }

        if (!subjectRepository.findById(journalFilter.getSubject_id()).isPresent()) {
            throw new EntityNotFoundException("Subject with id: " + journalFilter.getSubject_id() + " not found");
        }

        return journalRepository.findByStudentId(student_id)
                .stream()
                .filter(j -> j.getSubject().getId() == journalFilter.getSubject_id())
                .filter(j -> j.getDate().isBefore(LocalDate.parse(journalFilter.getEndDate()))
                        && j.getDate().isAfter(LocalDate.parse(journalFilter.getStartDate())))
                .filter(j -> {
                    if (journalFilter.isMark()) {
                        return j.getMark() != null &&
                                (j.getType().equals(journalFilter.getTypeOfWork()) ||
                                        journalFilter.getTypeOfWork().equalsIgnoreCase("all"));
                    } else {
                        return true;
                    }
                })
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalsByStudentId(int studentId) {
        List<Journal> journals = journalRepository.findAll();
        return journals
                .stream()
                .filter(j -> j.getStudent().getId() == studentId)
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalsBySubjectId(int subjectId) {
        List<Journal> journals = journalRepository.findAll();
        return journals
                .stream()
                .filter(j -> j.getSubject().getId() == subjectId)
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalsByStudentIdAndDate(int studentId, String dateFirst, String dateSecond) {
        LocalDate localDateFirst = LocalDate.parse(dateFirst);
        LocalDate localDateSecond = LocalDate.parse(dateSecond);
        List<Journal> journals = journalRepository.findAll();
        return journals
                .stream()
                .filter(j -> j.getStudent().getId() == studentId)
                .filter(j -> j.getDate().isBefore(localDateSecond) && j.getDate().isAfter(localDateFirst))
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public List<MainJournalDto> getJournalsByStudentIdAndType(int studentId, String type) {
        List<Journal> journals = journalRepository.findAll();
        return journals
                .stream()
                .filter(j -> j.getStudent().getId() == studentId)
                .filter(j -> j.getType().equals(type))
                .map(j -> modelMapper.map(j, MainJournalDto.class))
                .collect(Collectors.toList());
    }

    public long getAverageStudentSubjectMark(int studentId, int subjectId) {
        List<Journal> journals = journalRepository.findAll()
                .stream()
                .filter(j -> j.getStudent().getId() == studentId)
                .filter(j -> j.getSubject().getId() == subjectId)
                .collect(Collectors.toList());

        long average = 0;

        for (Journal j : journals) {
            average += j.getMark();
        }
        return Math.round((double) average / journals.size());
    }

    private Journal getJournalById(int id) {
        return journalRepository.findById(id).get();
    }


}
