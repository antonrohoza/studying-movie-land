package com.antonr.movieland.controller;

import static com.antonr.movieland.utils.FileReader.getAllLinesFromFileByURL;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class GenreController {

  private final GenreService genreService;

  @GetMapping("/genre/add")
  public List<Genre> addGenre() {
    return genreService.saveAll(genreService.getAllGenresFromFile(getAllLinesFromFileByURL(Constants.GENRE_FILE)));
  }
}
