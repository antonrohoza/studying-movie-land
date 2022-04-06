package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.service.GenreService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/genre")
public class GenreController {

  private final GenreService genreService;

  @GetMapping
  public List<Genre> getAllGenres() {
    return genreService.findAll();
  }

}
