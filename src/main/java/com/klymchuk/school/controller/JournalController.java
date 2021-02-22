package com.klymchuk.school.controller;

import com.klymchuk.school.dto.JournalDto;
import com.klymchuk.school.dto.JournalFilterDto;
import com.klymchuk.school.dto.MainJournalDto;
import com.klymchuk.school.dto.WorkTypePercentDto;
import com.klymchuk.school.service.JournalService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return journalService.getJournalByFilter(journalFilter, studentId);
    }

    @GetMapping("/average/")
    Long getAverageMark(int studentId, int subjectId) {
        return journalService.getAverageStudentSubjectMark(studentId, subjectId);
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
