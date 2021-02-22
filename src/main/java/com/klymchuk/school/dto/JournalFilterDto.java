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
public class JournalFilterDto {

    @ApiModelProperty(example = "2020-05-10")
    private String startDate;

    @ApiModelProperty(example = "2020-06-17")
    private String endDate;

    @ApiModelProperty(example = "1")
    private int subjectId;

    @ApiModelProperty(example = "true")
    private boolean isMark;

    @ApiModelProperty(example = "all")
    private String typeOfWork;

}
