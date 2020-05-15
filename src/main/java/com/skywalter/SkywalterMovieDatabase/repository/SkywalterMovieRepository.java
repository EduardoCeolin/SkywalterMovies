package com.skywalter.SkywalterMovieDatabase.repository;

import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.model.Movie;

import java.util.List;
import java.util.UUID;

public interface SkywalterMovieRepository {

    List<MovieDTO> findAll();

    Movie findById(UUID movieId);

    Movie save(Movie movie);

}
