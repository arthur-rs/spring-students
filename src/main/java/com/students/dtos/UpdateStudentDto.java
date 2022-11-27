package com.students.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Data
public class UpdateStudentDto {
    @NotBlank
    @Nullable
    private String name;

    @Nullable
    @Range(min = 0, max = 10)
    private Integer score;

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getScore() {
        return Optional.ofNullable(score);
    }
}
