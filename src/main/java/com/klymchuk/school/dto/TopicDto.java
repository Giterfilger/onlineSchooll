package com.klymchuk.school.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TopicDto {
    private String description;

    private String homework;

    private String resources;
}
