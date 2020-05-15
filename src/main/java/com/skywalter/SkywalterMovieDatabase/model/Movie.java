package com.skywalter.SkywalterMovieDatabase.model;

import com.skywalter.SkywalterMovieDatabase.dto.FormMovie;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "movies", schema = "skywalter")
public class Movie {

    @Id
    private UUID id;
    private String title;
    private LocalDate release;
    private String synopsis;
    private Integer userRating;

    private Movie(){
        this.id = UUID.randomUUID();
    }

    public Movie(String title, LocalDate release, String synopsis, Integer userRating) {
        this();
        this.title = title;
        this.release = release;
        this.synopsis = synopsis;
        this.userRating = userRating;
    }

    public Movie(FormMovie movie) {
        this();
        this.title = movie.getTitle();
        this.release = movie.getRelease();
        this.synopsis = movie.getSynopsis();
        this.userRating = movie.getUserRating();
    }

    public void update(FormMovie movie) {
        BeanUtils.copyProperties(movie, this, "id");
    }

}
