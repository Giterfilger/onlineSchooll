package com.klymchuk.school.service;

import com.klymchuk.school.dto.MainTeacherDto;
import com.klymchuk.school.dto.TeacherDto;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Teacher;
import com.klymchuk.school.repo.SubjectRepository;
import com.klymchuk.school.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;

    private final ModelMapper modelMapper;

    public MainTeacherDto getById(int id) {
        return modelMapper.map(getTeacherById(id), MainTeacherDto.class);
    }

    public MainTeacherDto save(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        return modelMapper.map(teacherRepository.save(teacher), MainTeacherDto.class);
    }

    public MainTeacherDto update(TeacherDto teacherDto, int id) {
        Teacher teacher = getTeacherById(id);
        modelMapper.map(teacherDto, teacher);
        return modelMapper.map(teacherRepository.save(teacher), MainTeacherDto.class);
    }

    public void delete(int id) {
        teacherRepository.delete(getTeacherById(id));
    }

    public List<MainTeacherDto> getAll() {
        return teacherRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, MainTeacherDto.class))
                .collect(Collectors.toList());
    }

    private Teacher getTeacherById(int id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id: " + id + "not found"));
    }

}
