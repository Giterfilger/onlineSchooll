package com.klymchuk.school.service;

import com.klymchuk.school.cloudinaryapi.CloudinaryManager;
import com.klymchuk.school.dto.MainStudentDto;
import com.klymchuk.school.dto.StudentDto;
import com.klymchuk.school.error.exceptions.BadImageException;
import com.klymchuk.school.error.exceptions.EntityNotFoundException;
import com.klymchuk.school.model.Student;
import com.klymchuk.school.repo.ClazzRepository;
import com.klymchuk.school.repo.StudentRepository;
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
public class StudentService {

    private final StudentRepository studentRepository;

    private final ClazzRepository clazzRepository;

    private final ModelMapper modelMapper;

    private final CloudinaryManager cloudinaryManager;

    public MainStudentDto getById(int id) {
        return modelMapper.map(getSubjectById(id), MainStudentDto.class);
    }

    public MainStudentDto save(StudentDto studentDto, int classId, MultipartFile image) {
        Student student = modelMapper.map(studentDto, Student.class);
        student.setClazz(clazzRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class with id: " + classId + "not found")));
        studentRepository.save(student);

        if (image != null) {
            try {
                student.setImageUrl(
                        cloudinaryManager.uploadImage(image, "student", student.getId()));
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new BadImageException("bad image for student with id: " + student.getId());
            }
        }

        return modelMapper.map(studentRepository.save(student), MainStudentDto.class);
    }

    public MainStudentDto update(StudentDto studentDto, int id) {
        Student student = getSubjectById(id);
        modelMapper.map(studentDto, student);


        return modelMapper.map(studentRepository.save(student), MainStudentDto.class);
    }

    public void delete(int id) {
        studentRepository.delete(getSubjectById(id));
    }

    public List<MainStudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .peek(s -> log.info("Student: " + s.getClazz()))
                .map(s -> modelMapper.map(s, MainStudentDto.class))
                .collect(Collectors.toList());
    }

    public Student getStudentByEmail(String email) {
        return studentRepository.getStudentByEmail(email);
    }

    private Student getSubjectById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id: " + id + "not found"));
    }
}
