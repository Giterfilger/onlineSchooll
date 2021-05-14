package com.klymchuk.school.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MainSubjectDto {

    private int id;
    private String name;
    private int countOfHour;
    private List<TopicDto> topics;
    private MainClazzDto clazz;
    private List<WorkTypePercentDto> workTypes;
}
