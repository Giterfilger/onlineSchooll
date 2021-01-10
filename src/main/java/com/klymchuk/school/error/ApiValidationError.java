package com.klymchuk.school.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ApiValidationError implements ApiSubError {

    private String field;
    private Object rejectedValue;
    private String message;

}