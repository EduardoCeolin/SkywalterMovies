package com.skywalter.SkywalterMovieDatabase.controller;

import com.skywalter.SkywalterMovieDatabase.dto.FormMovie;
import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.model.Movie;
import com.skywalter.SkywalterMovieDatabase.service.MovieService;
import com.skywalter.SkywalterMovieDatabase.utils.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> registerMovie(@RequestBody @Valid FormMovie movie) {
        Movie movieSaved = movieService.saveMovie(movie);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{movieId}")
                .buildAndExpand(movieSaved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{movieId}")
    public void updateMovie(@PathVariable("movieId") UUID movieId,
                            @RequestBody @Valid FormMovie movie) {
        movieService.updateMovie(movieId, movie);
    }

    @GetMapping
    public List<MovieDTO> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{movieId}")
    public MovieDTO findById(@PathVariable("movieId") UUID movieId) {
        return movieService.findById(movieId);
    }

    @GetMapping("/letter-metrics-top10")
    public List<Counter> letterMetricsTop10() {
        return movieService.letterMetricsTop10();
    }

}
