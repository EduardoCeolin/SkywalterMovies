package com.skywalter.SkywalterMovieDatabase.service;

import com.skywalter.SkywalterMovieDatabase.dto.FormMovie;
import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.model.Movie;
import com.skywalter.SkywalterMovieDatabase.repository.SkywalterMovieRepository;
import com.skywalter.SkywalterMovieDatabase.utils.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MovieService {

    @Autowired
    private SkywalterMovieRepository skywalterMovieRepository;

    public Movie saveMovie(FormMovie movie) {
        Movie movieToSave = new Movie(movie);
        return skywalterMovieRepository.save(movieToSave);
    }

    public Movie updateMovie(UUID movieId, FormMovie movie) {
        Movie movieSaved = skywalterMovieRepository.findById(movieId);
        movieSaved.update(movie);
        return skywalterMovieRepository.save(movieSaved);
    }

    public List<MovieDTO> findAll() {
        return skywalterMovieRepository.findAll();
    }

    public MovieDTO findById(UUID movieId) {
        Movie movieSaved = skywalterMovieRepository.findById(movieId);
        return new MovieDTO(movieSaved);
    }

    public List<Counter> letterMetricsTop10() {
        List<MovieDTO> movies = skywalterMovieRepository.findAll();
        LetterCounter letterCounter = new LetterCounter(movies);
        return letterCounter.count();
    }

}
