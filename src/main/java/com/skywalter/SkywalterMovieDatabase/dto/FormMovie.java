package com.skywalter.SkywalterMovieDatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FormMovie {

    @NotBlank
    @Size(min = 5, max = 30)
    private String title;

    @NotNull
    private LocalDate release;

    @NotEmpty
    private String synopsis;

    @Max(10)
    @PositiveOrZero
    private Integer userRating;

    public FormMovie() {
    }

}
