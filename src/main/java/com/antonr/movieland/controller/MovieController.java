package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.dto.ConvertorDto;
import com.antonr.movieland.entity.dto.MovieWithPicturePath;
import com.antonr.movieland.entity.request.MovieRequest;
import com.antonr.movieland.entity.request.SortDirection;
import com.antonr.movieland.entity.request.SortField;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/v1/movie")
public class MovieController {

  private final MovieService movieService;
  private final GenreService genreService;

  @GetMapping
  public List<MovieWithPicturePath> getAllMoviesSortedByRating(@RequestParam Optional<String> rating,
                                                               @RequestParam Optional<String> price) {
    if (rating.isEmpty() && price.isEmpty()) {
      log.info("get all movies without sorting");
      return ConvertorDto.movieDto(movieService.findAll());
    }
    return rating.isPresent() ? ConvertorDto.movieDto(movieService.sortedMoviesByRating(rating.get()))
                              : ConvertorDto.movieDto(movieService.sortedMoviesByPrice(price.get()));
  }

  @GetMapping(value = "/random")
  public List<MovieWithPicturePath> getRandomMovies() {
    log.info("get {} random movies", Constants.RANDOM_NUMBER_OF_MOVIES);
    return ConvertorDto.movieDto(movieService.getRandomNumberOfMovies(Constants.RANDOM_NUMBER_OF_MOVIES));
  }

  @GetMapping(value = "genre/{genreId}")
  public List<MovieWithPicturePath> getMoviesByGenreId(@PathVariable Long genreId,
                                                       @RequestParam Optional<String> rating,
                                                       @RequestParam Optional<String> price) {
    if (rating.isEmpty() && price.isEmpty()) {
      log.info("get movies by genre id = {} without sorting", genreId);
      return ConvertorDto.movieDto(genreService.getById(genreId).getMovies());
    }
    return rating.isPresent() ? getSortedMoviesByGenreId(genreId, new MovieRequest(SortField.RATING, SortDirection.findDirectionByOrder(rating.get())))
                              : getSortedMoviesByGenreId(genreId, new MovieRequest(SortField.PRICE, SortDirection.findDirectionByOrder(price.get())));
  }

  @GetMapping("{movieId}")
  public Movie getMovieById(@PathVariable Long movieId,
                            @RequestParam Optional<String> currency){
    log.info("get movies by id = {}", movieId);
    return currency.isPresent() ? movieService.getMovieByIdWithCurrency(movieId, currency.get())
                                : movieService.getMovieById(movieId);
  }

  private List<MovieWithPicturePath> getSortedMoviesByGenreId(Long genreId, MovieRequest movieRequest) {
    log.info("get sorted movies by genre id = {} by sort field {} with {} sorting", genreId, movieRequest.getSortField().getName(), movieRequest.getSortDirection().getDirectionOrder());
    return ConvertorDto.movieDto(movieService.sortedByGenreId(genreId, movieRequest));
  }

}
