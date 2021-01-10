package com.klymchuk.school.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainJournalDto {

    private int id;
    private LocalDate date;
    private Integer mark;
    private boolean visiting;
    private String type;
    private StudentDto student;
    private MainSubjectDto subject;

}

