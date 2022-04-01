package com.antonr.movieland.service;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {

  private final GenreRepository genreRepository;

  public Genre addGenre(Genre genre) {
    return genreRepository.save(genre);
  }
}
