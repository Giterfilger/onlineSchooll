package com.klymchuk.school.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WorkTypePercentDto {
    String name;
    double percent;
}
