package com.antonr.movieland.service;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.request.MovieRequest;
import java.util.List;

public interface MovieService {

  List<Movie> findAll();

  List<Movie> sortedMoviesByRating(String order);

  List<Movie> sortedMoviesByPrice(String order);

  List<Movie> sortedByGenreId(Long id, MovieRequest movieRequest);

  List<Movie> getRandomNumberOfMovies(int randomNumberOfMovies);

  Movie getMovieById(Long movieId);

  Movie getMovieByIdWithCurrency(Long movieId, String currency);

}
