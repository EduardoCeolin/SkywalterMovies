package com.skywalter.SkywalterMovieDatabase.repository;

import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SkywalterMovieRepositoryImpl implements SkywalterMovieRepository {

    @Autowired
    private SkywalterMovieRepositoryJPA skywalterMovieRepositoryJPA;

    @Override
    public Movie save(Movie movie) {
        return skywalterMovieRepositoryJPA.save(movie);
    }

    @Override
    public List<MovieDTO> findAll() {
        return skywalterMovieRepositoryJPA.findAll().stream()
                .map(MovieDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Movie findById(UUID movieId) {
        Optional<Movie> movieOptional = skywalterMovieRepositoryJPA.findById(movieId);
        return movieOptional.orElseThrow(() -> new EntityNotFoundException("THIS MOVIE WASN'T FOUND IN DATABASE"));
    }

}
