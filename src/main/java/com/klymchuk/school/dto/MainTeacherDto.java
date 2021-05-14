package com.klymchuk.school.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainTeacherDto {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String imageUrl;
    private List<MainSubjectDto> subjects = new ArrayList<>();
}
