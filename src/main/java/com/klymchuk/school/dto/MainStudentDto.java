package com.klymchuk.school.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainStudentDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String imageUrl;
    private MainClazzDto clazz;

}
