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

    @ApiModelProperty(example = "Olena")
    private String name;

    @ApiModelProperty(example = "Ternavska")
    private String surname;

    @ApiModelProperty(example = "olena@gmail.com")
    private String email;

    @ApiModelProperty(example = "rjfoun4kb4")
    private String phone;

}
