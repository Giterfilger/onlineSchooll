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

    @GetMapping("/class/{id}")
    List<MainStudentDto> getStudentsByClassId(@PathVariable int id) {
        return studentService.getByClassId(id);
    }

    @PostMapping("/")
    MainStudentDto saveStudent(int classId,
                               @RequestBody StudentDto studentDto) {
        return studentService.save(studentDto, classId);
    }

    @PostMapping("/image")
    void uploadStudentImage(@RequestParam("file") MultipartFile image, @RequestParam int id) {
        studentService.uploadImage(image, id);
    }

    @GetMapping("/email")
    MainStudentDto getStudentByEmail(@RequestParam String email) {
        return studentService.getMainStudentDtoByEmail(email);
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
