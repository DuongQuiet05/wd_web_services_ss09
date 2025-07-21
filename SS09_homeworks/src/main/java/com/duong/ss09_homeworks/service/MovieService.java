package com.duong.ss09_homeworks.service;

import com.duong.ss09_homeworks.model.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    Movie updateMovie(Long id, Movie newMovie);
    Movie deleteMovie(Long id);
    List<Movie> getAllMovies(String searchMovie);

}