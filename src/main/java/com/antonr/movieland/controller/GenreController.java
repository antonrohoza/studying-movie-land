package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.service.GenreService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/genre")
public class GenreController {

  private final GenreService genreService;

  @GetMapping
  public List<Genre> getAllGenres() {
    log.info("get all genres");
    return genreService.findAll();
  }

}
