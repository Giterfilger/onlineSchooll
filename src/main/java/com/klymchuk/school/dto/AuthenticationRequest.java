package com.klymchuk.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @ApiModelProperty(example = "andrii@gmail.com")
    private String email;

    @ApiModelProperty(example = "pass")
    private String password;
}
