package com.antonr.movieland.service;

import com.antonr.movieland.entity.Poster;
import com.antonr.movieland.repository.PosterRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PosterService {

  private static final String IDENTIFIER_OF_URL = "http";
  private final PosterRepository posterRepository;

  public List<Poster> saveAll(List<Poster> genres) {
    return posterRepository.saveAll(genres);
  }

  public List<Poster> getAllPostersFromFile(List<String> allLines) {
    return allLines.stream()
                   .map(this::getGenreFromStr)
                   .collect(Collectors.toList());
  }

  private Poster getGenreFromStr(String line) {
    String[] elements = line.split(IDENTIFIER_OF_URL);
    return Poster.builder()
                 .movie_name(elements[0].trim())
                 .url(IDENTIFIER_OF_URL + elements[1].trim())
                 .build();
  }
}
