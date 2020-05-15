package com.skywalter.SkywalterMovieDatabase.dto;

import com.skywalter.SkywalterMovieDatabase.model.Movie;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class MovieDTO {

    private UUID id;
    private String title;
    private LocalDate release;
    private String synopsis;
    private Integer userRating;

    public MovieDTO() {
    }

    public MovieDTO(Movie movie) {
        BeanUtils.copyProperties(movie, this);
    }

}
