package com.klymchuk.school.dto;

import lombok.*;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingDto {

    private int id;
    private String name;
    private String surname;
    private double rating;

}
