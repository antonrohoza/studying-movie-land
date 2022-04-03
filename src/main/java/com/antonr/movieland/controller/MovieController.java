package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import com.antonr.movieland.utils.FileReader;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/movie/add")
  public Pair<List<Movie>, Set<Country>> addMovie() {
    Pair<List<Movie>, Set<Country>> moviesAndCountries = movieService.generateMoviesWithCountries(
        FileReader.getAllLinesFromFileByURL(Constants.MOVIE_FILE));

    return moviesAndCountries;
  }

}
