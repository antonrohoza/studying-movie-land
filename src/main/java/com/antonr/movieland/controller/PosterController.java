package com.antonr.movieland.controller;

import static com.antonr.movieland.utils.FileReader.getAllLinesFromFileByURL;

import com.antonr.movieland.entity.Poster;
import com.antonr.movieland.service.PosterService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class PosterController {

  private final PosterService posterService;

  @GetMapping("/poster/add")
  public List<Poster> addPoster() {
    return posterService.saveAll(posterService.getAllPostersFromFile(getAllLinesFromFileByURL(Constants.POSTER_FILE)));
  }
}
