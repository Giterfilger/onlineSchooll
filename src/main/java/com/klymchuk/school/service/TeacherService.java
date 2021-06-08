package com.klymchuk.school.service;

import com.klymchuk.school.cloudinaryapi.CloudinaryManager;
import com.klymchuk.school.dto.MainTeacherDto;
import com.klymchuk.school.dto.TeacherDto;
import com.klymchuk.school.error.exceptions.BadImageException;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Teacher;
import com.klymchuk.school.repo.SubjectRepository;
import com.klymchuk.school.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;

    private final ModelMapper modelMapper;

    private final CloudinaryManager cloudinaryManager;

    public MainTeacherDto getById(int id) {
        log.info("-----------------------");
        log.info("Teacher service: {}", id);
        log.info("{}", getTeacherById(id));
        log.info("{}", modelMapper.map(getTeacherById(id), MainTeacherDto.class));
        return modelMapper.map(getTeacherById(id), MainTeacherDto.class);
    }

    public MainTeacherDto save(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto, Teacher.class);
        log.info("Teacher Dto: " + teacherDto.toString());
        log.info("Teacher: " + teacher.toString());
        return modelMapper.map(teacherRepository.save(teacher), MainTeacherDto.class);
    }

    public void uploadImage(MultipartFile image, int id){
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id: " + id + "not found"));
        log.info("Image equal null: " + (image == null));
        if (image != null) {
            try {
                teacher.setImageUrl(
                        cloudinaryManager.uploadImage(image, "teacher", teacher.getId()));
                teacherRepository.save(teacher);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new BadImageException("bad image for student with id: " + teacher.getId());
            }
        }
    }

    public MainTeacherDto update(TeacherDto teacherDto, int id) {
        Teacher teacher = getTeacherById(id);
        log.info(teacher.toString());
        modelMapper.map(teacherDto, teacher);
        log.info(teacher.toString());
        log.info(teacherDto.toString());
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

    public MainTeacherDto getTeacherByEmail(String email) {
        return modelMapper.map(teacherRepository.getTeacherByEmail(email), MainTeacherDto.class);
    }
}
