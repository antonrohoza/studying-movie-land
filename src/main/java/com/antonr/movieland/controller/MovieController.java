package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.entity.dto.ConvertorDto;
import com.antonr.movieland.entity.dto.MovieWithPicturePath;
import com.antonr.movieland.entity.request.MovieRequest;
import com.antonr.movieland.entity.request.SortDirection;
import com.antonr.movieland.entity.request.SortField;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieController {

  private final MovieService movieService;
  private final GenreService genreService;

  @GetMapping
  public List<MovieWithPicturePath> getAllMoviesSortedByRating(
      @RequestParam(value = "rating", required = false, defaultValue = "") String rating,
      @RequestParam(value = "price", required = false, defaultValue = "") String price) {
    if (rating.isEmpty() && price.isEmpty()) {
      return ConvertorDto.movieDto(movieService.findAll());
    } else if (rating.isEmpty()) {
      return ConvertorDto.movieDto(movieService.sortedMoviesByPrice(price));
    }
    return ConvertorDto.movieDto(movieService.sortedMoviesByRating(rating));
  }

  @GetMapping(value = "/random")
  public List<MovieWithPicturePath> getRandomMovies() {
    return ConvertorDto
        .movieDto(movieService.findRandomNumberOfMovies(Constants.RANDOM_NUMBER_OF_MOVIES));
  }

  @GetMapping(value = "genre/{genreId}")
  public List<MovieWithPicturePath> getMoviesByGenreId(@PathVariable Long genreId,
                                                       @RequestParam(value = "rating", required = false, defaultValue = "") String rating,
                                                       @RequestParam(value = "price", required = false, defaultValue = "") String price) {
    Genre currentGenre = genreService.getById(genreId);
    if (rating.isEmpty() && price.isEmpty()) {
      return ConvertorDto.movieDto(currentGenre.getMovies());
    } else if (rating.isEmpty()) {
      return ConvertorDto.movieDto(movieService.sortedByGenreId(genreId, new MovieRequest(
          SortField.PRICE, SortDirection.findDirectionByOrder(price))));
    }
    return ConvertorDto.movieDto(movieService.sortedByGenreId(genreId, new MovieRequest(
        SortField.RATING, SortDirection.findDirectionByOrder(price))));
  }

}
