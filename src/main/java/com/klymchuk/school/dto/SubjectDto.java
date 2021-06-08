package com.klymchuk.school.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDto {

    @ApiModelProperty(example = "Physics")
    private String name;

    private int countOfHour;

    private List<WorkTypePercentDto> workTypes;

}
