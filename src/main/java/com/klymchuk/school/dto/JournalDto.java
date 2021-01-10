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
public class JournalDto {

    @ApiModelProperty(example = "2020-05-17")
    private String date;

    @ApiModelProperty(example = "9")
    private int mark;

    @ApiModelProperty(example = "class work")
    private String type;

}
