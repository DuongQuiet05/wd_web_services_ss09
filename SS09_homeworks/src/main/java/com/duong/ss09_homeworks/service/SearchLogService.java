package com.duong.ss09_homeworks.service;

import com.duong.ss09_homeworks.model.entity.Movie;
import com.duong.ss09_homeworks.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchLogService {

    private final Path logPath = Paths.get("logs/app.log");
    private final Pattern searchPattern = Pattern.compile("Tìm kiếm phim với từ khóa: '(.+?)'");

    private final MovieRepository movieRepository;

    public SearchLogService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Map<String, Integer> getSearchKeywordFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(logPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = searchPattern.matcher(line);
                if (matcher.find()) {
                    String keyword = matcher.group(1).toLowerCase();
                    frequencyMap.put(keyword, frequencyMap.getOrDefault(keyword, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frequencyMap;
    }

    public List<Movie> getSuggestedMovies() {
        Set<String> keywords = getSearchKeywordFrequency().keySet();
        Set<Movie> result = new HashSet<>();
        for (String keyword : keywords) {
            result.addAll(movieRepository.findByTitleContainingIgnoreCase(keyword));
        }
        return new ArrayList<>(result);
    }
}