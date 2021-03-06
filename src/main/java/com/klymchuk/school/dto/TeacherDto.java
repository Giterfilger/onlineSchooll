package com.klymchuk.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String imageUrl;
    private String role;

}
