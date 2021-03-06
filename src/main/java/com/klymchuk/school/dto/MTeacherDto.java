package com.klymchuk.school.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MTeacherDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String schoolName;

}
