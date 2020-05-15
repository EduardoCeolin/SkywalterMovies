package com.skywalter.SkywalterMovieDatabase.repository;

import com.skywalter.SkywalterMovieDatabase.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkywalterMovieRepositoryJPA extends JpaRepository<Movie, UUID> {
}