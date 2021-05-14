package com.klymchuk.school.controller;

import com.klymchuk.school.dto.MTeacherDto;
import com.klymchuk.school.service.MainTeacherService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/mainTeacher")
@RequiredArgsConstructor
@Api(tags = "MainTeacher")
public class MainTeacherController {

    private final MainTeacherService mainTeacherService;

    @PostMapping("/")
    void saveMainTeacher(MTeacherDto mTeacherDto){
        mainTeacherService.saveTeacher(mTeacherDto);
    }

}
