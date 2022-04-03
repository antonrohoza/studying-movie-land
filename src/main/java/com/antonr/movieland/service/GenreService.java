package com.antonr.movieland.service;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.repository.GenreRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

  private final GenreRepository genreRepository;

  public List<Genre> saveAll(List<Genre> genres) {
    return genreRepository.saveAll(genres);
  }

  public List<Genre> getAllGenresFromFile(List<String> allLines) {
    return allLines.stream()
                   .map(Genre::createGenreByGenreName)
                   .collect(Collectors.toList());
  }
}
