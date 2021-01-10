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
public class StudentDto {

    @ApiModelProperty(example = "Ostap")
    private String name;

    @ApiModelProperty(example = "Vushnia")
    private String surname;

    @ApiModelProperty(example = "som@gmail.com")
    private String email;

    @ApiModelProperty(example = "VushniaChereshnia")
    private String phone;

}
