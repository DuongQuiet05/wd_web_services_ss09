package com.duong.ss09_homeworks.service;

import com.duong.ss09_homeworks.model.entity.Movie;
import com.duong.ss09_homeworks.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie createMovie(Movie movie) {
        try {
            Movie saved = movieRepository.save(movie);
            logger.info("🎬 Thêm phim thành công: '{}' vào lúc {}", saved.getTitle(), LocalDateTime.now());
            return saved;
        } catch (Exception e) {
            logger.error("\u001B[31m Lỗi khi thêm phim: {}\u001B[0m", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Movie updateMovie(Long id, Movie newMovie) {
        try {
            Optional<Movie> optional = movieRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Không tìm thấy phim để cập nhật.");
            }

            Movie oldMovie = optional.get();
            Movie updated = movieRepository.save(Movie.builder()
                    .id(id)
                    .title(newMovie.getTitle())
                    .description(newMovie.getDescription())
                    .releaseDate(newMovie.getReleaseDate())
                    .poster(newMovie.getPoster())
                    .build());

            logger.info(
                    "\u001B[33mThông tin cũ: {}\u001B[0m\n\u001B[32mThông tin mới: {}\u001B[0m",
                    oldMovie, updated
            );

            return updated;
        } catch (Exception e) {
            logger.error("\u001B[31m Lỗi khi cập nhật phim: {}\u001B[0m", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Movie deleteMovie(Long id) {
        try {
            Optional<Movie> optional = movieRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Không tìm thấy phim để xóa.");
            }

            Movie deleted = optional.get();
            movieRepository.deleteById(id);

            logger.info("\u001B[31mXóa thành công\u001B[0m - \u001B[32m{}\u001B[0m", deleted);
            return deleted;
        } catch (Exception e) {
            logger.error("\u001B[31m Lỗi khi xóa phim: {}\u001B[0m", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Movie> getAllMovies(String searchMovie) {
        long startTime = System.currentTimeMillis();

        List<Movie> movies;
        if (searchMovie != null && !searchMovie.isBlank()) {
            movies = movieRepository.findByTitleContainingIgnoreCase(searchMovie);
        } else {
            movies = movieRepository.findAll();
        }

        long endTime = System.currentTimeMillis();

        logger.info("\u001B[32m🔍 Tìm kiếm phim với từ khóa: '{}', số lượng: {}, thời gian: {}ms\u001B[0m",
                searchMovie != null ? searchMovie : "Không có",
                movies.size(),
                (endTime - startTime)
        );

        return movies;
    }

}