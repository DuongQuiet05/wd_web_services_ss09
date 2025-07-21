package com.duong.ss09_homeworks.controller;

import com.duong.ss09_homeworks.model.entity.Movie;
import com.duong.ss09_homeworks.service.MovieService;
import com.duong.ss09_homeworks.service.SearchLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final SearchLogService searchLogService;
    @PostMapping
    public String createMovie(@RequestBody Movie movie) {
        try {
            movieService.createMovie(movie);
            return "Thêm phim thành công!";
        } catch (Exception e) {
            return "Đã xảy ra lỗi khi thêm phim!";
        }
    }

    @PutMapping("/{id}")
    public String updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            movieService.updateMovie(id, movie);
            return "Cập nhật phim thành công!";
        } catch (Exception e) {
            return "Đã xảy ra lỗi khi cập nhật phim!";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return "Xóa phim thành công!";
        } catch (Exception e) {
            return "Đã xảy ra lỗi khi xóa phim!";
        }
    }

    @GetMapping
    public List<Movie> getAllMovies(@RequestParam(required = false) String searchMovie) {
        return movieService.getAllMovies(searchMovie);
    }


    @GetMapping("/search-logs")
    public Map<String, Integer> getSearchLogs() {
        return searchLogService.getSearchKeywordFrequency();
    }

    @GetMapping("/suggestions")
    public List<Movie> getSuggestions() {
        return searchLogService.getSuggestedMovies();
    }


}