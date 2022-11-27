package com.students.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateStudentDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Range(min = 0, max = 10)
    private Integer score;
}
