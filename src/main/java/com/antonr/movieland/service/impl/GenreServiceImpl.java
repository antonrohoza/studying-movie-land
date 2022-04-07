package com.antonr.movieland.service.impl;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.repository.jpa.JpaGenreRepository;
import com.antonr.movieland.service.GenreService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final JpaGenreRepository jpaGenreRepository;
  private volatile List<Genre> cachedGenreList;

  public List<Genre> findAll() {
    return new ArrayList<>(cachedGenreList);
  }

  public Genre getById(Long id) {
    return jpaGenreRepository.getById(id);
  }

  @PostConstruct
  private void init() {
    fillCache();
  }

  @Scheduled(cron = "0 0 */4 ? * *")
  private void fillCache() {
    cachedGenreList = jpaGenreRepository.findAll();
  }

}
