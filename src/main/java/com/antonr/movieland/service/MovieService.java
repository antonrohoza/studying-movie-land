package com.antonr.movieland.service;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.repository.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

  MovieRepository movieRepository;

  public List<Movie> findAll() {
    return movieRepository.findAll();
  }

  public List<Movie> sortedMoviesByRating(String order) {
    return "asc".equals(order) ? movieRepository.getSortedMoviesByRatingAsc()
                               : movieRepository.getSortedMoviesByRatingDesc();
  }

  public List<Movie> sortedMoviesByPrice(String order) {
    return "asc".equals(order) ? movieRepository.getSortedMoviesByPriceAsc()
                               : movieRepository.getSortedMoviesByPriceDesc();
  }

  public List<Movie> sortedMoviesByGenreIdByPrice(String order, List<Movie> currentGenreMovies) {
    return sortedMoviesByPrice(order).stream()
                                     .filter(currentGenreMovies::contains)
                                     .collect(Collectors.toList());
  }

  public List<Movie> sortedMoviesByGenreIdByRating(String order, List<Movie> currentGenreMovies) {
    return sortedMoviesByRating(order).stream()
                                      .filter(currentGenreMovies::contains)
                                      .collect(Collectors.toList());
  }
}
