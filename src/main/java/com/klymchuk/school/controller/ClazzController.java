package com.klymchuk.school.controller;

import com.klymchuk.school.dto.ClazzDto;
import com.klymchuk.school.dto.MainClazzDto;
import com.klymchuk.school.error.ApiError;
import com.klymchuk.school.service.ClazzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
@Api(tags = "Class")
public class ClazzController {

    private final ClazzService clazzService;

    @GetMapping("/")
    List<MainClazzDto> getClasses() {
        return clazzService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponse(code = 404, message = "Class not found", response = ApiError.class)
    MainClazzDto getClassById(@PathVariable int id) {
        return clazzService.getById(id);
    }

    @PostMapping("/")
    MainClazzDto saveClass(@RequestBody ClazzDto clazzDto) {
        return clazzService.save(clazzDto);

    }

    @PutMapping("/{id}")
    MainClazzDto updateClass(@PathVariable int id, @RequestBody ClazzDto clazzDto) {
        return clazzService.update(clazzDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteClass(@PathVariable int id) {
        clazzService.delete(id);
    }

}
