package com.klymchuk.school.controller;

import com.klymchuk.school.dto.MainSubjectDto;
import com.klymchuk.school.dto.SubjectDto;
import com.klymchuk.school.service.SubjectService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@Api(tags = "Subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/")
    List<MainSubjectDto> getSubjects() {
        return subjectService.getAll();
    }

    @GetMapping("/{id}")
    MainSubjectDto getSubjectById(@PathVariable int id) {
        return subjectService.getById(id);
    }

    @PostMapping("/")
    MainSubjectDto saveSubject(@RequestBody SubjectDto subjectDto) {
        return subjectService.save(subjectDto);
    }

    @PutMapping("/{id}")
    MainSubjectDto updateSubject(@PathVariable int id, @RequestBody SubjectDto subjectDto) {
        return subjectService.update(subjectDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteSubject(@PathVariable int id) {
        subjectService.delete(id);
    }

}
