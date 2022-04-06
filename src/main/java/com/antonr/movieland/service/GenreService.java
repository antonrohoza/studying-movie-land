package com.antonr.movieland.service;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.repository.jpa.JpaGenreRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {

  private final JpaGenreRepository jpaGenreRepository;

  public List<Genre> findAll() {
    return jpaGenreRepository.findAll();
  }

  public Genre getById(Long id) {
    return jpaGenreRepository.getById(id);
  }

}
