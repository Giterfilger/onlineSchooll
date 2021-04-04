package com.klymchuk.school.dto;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @ApiModelProperty(example = "andrii@gmail.com")
    @NotNull
    private String email;

    @ApiModelProperty(example = "pass")
    @NotNull
    private String password;
}
