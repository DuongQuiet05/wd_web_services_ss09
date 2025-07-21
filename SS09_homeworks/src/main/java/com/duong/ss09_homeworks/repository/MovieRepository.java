package com.duong.ss09_homeworks.repository;

import com.duong.ss09_homeworks.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCase(String keyword);

}