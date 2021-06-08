package com.klymchuk.school.controller;

import com.klymchuk.school.dto.*;
import com.klymchuk.school.model.Student;
import com.klymchuk.school.service.JournalService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/journals")
@RequiredArgsConstructor
@Api(tags = "Journal")
public class JournalController {

    private final JournalService journalService;

    @GetMapping("/")
    List<MainJournalDto> getJournals() {
        return journalService.getAll();
    }

    @GetMapping("/{id}")
    MainJournalDto getJournalById(@PathVariable int id) {
        return journalService.getById(id);
    }

    @PostMapping("/filter/")
    List<MainJournalDto> getJournalsByFilter(@RequestBody JournalFilterDto journalFilter, int studentId) {
        log.info(String.valueOf(studentId));
        log.info(journalFilter.toString());
        return journalService.getJournalByFilter(journalFilter, studentId);
    }

    @PostMapping("/teacher/filter/")
    List<MainJournalDto> getJournalsByTeacherFilter(@RequestBody JournalTeacherFilterDto journalFilter) {
        log.info(journalFilter.toString());
        return journalService.getJournalByTeacherFilter(journalFilter);
    }

    @GetMapping("/average/")
    Long getAverageMark(int studentId, int subjectId) {
        return journalService.getAverageStudentSubjectMark(studentId, subjectId);
    }

    @GetMapping("/rating")
    Integer getStudentRatingBySubjectId(int classId, int studentId, int subjectId){
        return journalService.getStudentRatingBySubjectId(classId, studentId, subjectId);
    }

    @GetMapping("/rating/month")
    Map<Month, Double> getAverageMarkOfMonth(int studentId, int subjectId) {
        return journalService.getAverageMarkOfMonth(studentId, subjectId);
    }

    @GetMapping("/rating/class/{id}")
    List<RatingDto> getAverageMarkByClass(@PathVariable int id, int subjectId){
        return journalService.getAverageMarkByClass(id, subjectId);
    }

    @GetMapping("/percent/")
    List<WorkTypePercentDto> getPercentOfWorkType(int studentId, int subjectId) {
        return journalService.getPercentOfWorkType(subjectId, studentId);
    }

    @PostMapping("/")
    MainJournalDto saveJournal(int studentId, int subjectId, @RequestBody JournalDto journalDto) {
        return journalService.save(journalDto, subjectId, studentId);
    }

    @PutMapping("/{id}")
    MainJournalDto updateJournal(@PathVariable int id, @RequestBody JournalDto journalDto) {
        return journalService.update(journalDto, id);
    }

    @DeleteMapping("/{id}")
    void deleteJournal(@PathVariable int id) {
        journalService.delete(id);
    }

}
