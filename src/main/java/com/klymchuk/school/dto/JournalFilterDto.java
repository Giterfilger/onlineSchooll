package com.klymchuk.school.dto;

import io.swagger.annotations.ApiModelProperty;
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
public class JournalFilterDto {

    @ApiModelProperty(example = "2020-05-10")
    private String startDate;

    @ApiModelProperty(example = "2020-06-17")
    private String endDate;

    @ApiModelProperty(example = "[1,2,3]")
    private List<Integer> subjectIds = new ArrayList<>();

    @ApiModelProperty(example = "true")
    private boolean isMark;

    @ApiModelProperty(example = "all")
    private List<String> typesOfWork = new ArrayList<>();

}
