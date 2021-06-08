package com.klymchuk.school.service;

import com.klymchuk.school.dto.MainSubjectDto;
import com.klymchuk.school.dto.SubjectDto;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Clazz;
import com.klymchuk.school.model.Subject;
import com.klymchuk.school.model.Teacher;
import com.klymchuk.school.model.WorkType;
import com.klymchuk.school.repo.ClazzRepository;
import com.klymchuk.school.repo.SubjectRepository;
import com.klymchuk.school.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClazzRepository clazzRepository;

    private final ModelMapper modelMapper;

    public MainSubjectDto getById(int id) {
        return modelMapper.map(getSubjectById(id), MainSubjectDto.class);
    }

    public MainSubjectDto save(SubjectDto subjectDto, int teacherId, int clazzId) {
        log.info(subjectDto.toString());

        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);

        Subject subject = modelMapper.map(subjectDto, Subject.class);
        subject.setTeacher(teacher.orElseThrow(() ->
                new EntityNotFoundException("Teacher with id: " + teacherId + "not found")));
        subject.setClazz(clazz.orElseThrow(() ->
                new EntityNotFoundException("Clazz with id: " + clazzId + "not found")));
        MainSubjectDto mainSubjectDto = modelMapper.map(subjectRepository.save(subject), MainSubjectDto.class);

        log.info(mainSubjectDto.toString());
        return mainSubjectDto;
    }

    public MainSubjectDto update(SubjectDto subjectDto, int id) {
        Subject subject = getSubjectById(id);
        modelMapper.map(subjectDto, subject);
        return modelMapper.map(subjectRepository.save(subject), MainSubjectDto.class);
    }

    public void delete(int id) {
        subjectRepository.delete(getSubjectById(id));
    }

    public List<MainSubjectDto> getAll() {
        log.info("in get all");
        log.info("sdv + " + subjectRepository.findAll());
        return subjectRepository.findAll()
                .stream()
                .peek(mm -> {log.info(mm.toString());})
                .map(c -> modelMapper.map(c, MainSubjectDto.class))
                .peek(m -> {log.info(m.toString());})
                .collect(Collectors.toList());
    }

    public List<MainSubjectDto> getSubjectsByClazzId(int clazzId){
        return subjectRepository.findSubjectByClazzId(clazzId)
                .stream()
                .map(s -> modelMapper.map(s, MainSubjectDto.class))
                .peek(m -> {log.info(m.toString());})
                .collect(Collectors.toList());
    }

    private Subject getSubjectById(int id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id: " + id + "not found"));
    }

}
