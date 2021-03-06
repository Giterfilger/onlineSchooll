package com.klymchuk.school.controller;

import com.klymchuk.school.dto.MainTeacherDto;
import com.klymchuk.school.dto.TeacherDto;
import com.klymchuk.school.service.TeacherService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Api(tags = "Teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/")
    List<MainTeacherDto> getTeacher() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    MainTeacherDto getTeacherById(@PathVariable int id) {
        return teacherService.getById(id);
    }

    @GetMapping("/email")
    MainTeacherDto getTeacherByEmail(@RequestParam String email){
        return teacherService.getTeacherByEmail(email);
    }

    @PostMapping("/")
    MainTeacherDto saveTeacher(@RequestBody TeacherDto teacherDto) {
        return teacherService.save(teacherDto);
    }

    @PostMapping("/image")
    void uploadTeacherImage(@RequestParam("file") MultipartFile image, @RequestParam int id){
        teacherService.uploadImage(image, id);
    }

    @PutMapping("/{id}")
    MainTeacherDto updateTeacher(@PathVariable int id, @RequestBody TeacherDto teacherDto) {
        return teacherService.update(teacherDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteTeacher(@PathVariable int id) {
        teacherService.delete(id);
    }

}
