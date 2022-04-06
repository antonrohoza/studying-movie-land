package com.antonr.movieland.service;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.repository.GenreRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

  private final GenreRepository genreRepository;

  public List<Genre> findAll() {
    return genreRepository.findAll();
  }

  public Genre getById(Long id) {
    return genreRepository.getById(id);
  }

}
