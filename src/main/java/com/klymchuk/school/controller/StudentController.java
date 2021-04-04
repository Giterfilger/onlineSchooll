package com.klymchuk.school.controller;

import com.klymchuk.school.dto.MainStudentDto;
import com.klymchuk.school.dto.StudentDto;
import com.klymchuk.school.service.StudentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@Api(tags = "Student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    List<MainStudentDto> getStudent() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    MainStudentDto getStudentById(@PathVariable int id) {
        return studentService.getById(id);
    }

    @GetMapping("/me")
    MainStudentDto me(){
        return studentService.currentUser();
    }

    @PostMapping("/")
    MainStudentDto saveStudent(int classId,
                               StudentDto studentDto,
                               @RequestParam(value = "file", required = false) MultipartFile image ) {
        return studentService.save(studentDto, classId, image);
    }

    @PutMapping("/{id}")
    MainStudentDto updateStudent(@PathVariable int id, @RequestBody StudentDto studentDto) {
        return studentService.update(studentDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteStudent(@PathVariable int id) {
        studentService.delete(id);
    }

}
