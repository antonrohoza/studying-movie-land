package com.antonr.movieland.controller;

import static com.antonr.movieland.util.FileReader.getAllLinesFromFileByURL;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/genres")
@AllArgsConstructor
public class GenreController {

  private final GenreService genreService;

  @GetMapping("/add")
  public String addGenre() {

    getAllLinesFromFileByURL(Constants.GENRE_FILE).stream()
                                       .map(Genre::new)
                                       .forEach(genreService::addGenre);

    return "redirect:/users";
  }
}
