package com.antonr.movieland.service;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.request.MovieRequest;
import com.antonr.movieland.entity.request.SortDirection;
import com.antonr.movieland.repository.jpa.JpaMovieRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

  JpaMovieRepository jpaMovieRepository;

  public List<Movie> findAll() {
    return jpaMovieRepository.findAll();
  }

  public List<Movie> sortedMoviesByRating(String order) {
    return SortDirection.ASC.getDirectionOrder().equals(order.toLowerCase()) ? jpaMovieRepository.getSortedMoviesByRatingAsc()
                                                                             : jpaMovieRepository.getSortedMoviesByRatingDesc();
  }

  public List<Movie> sortedMoviesByPrice(String order) {
    return SortDirection.ASC.getDirectionOrder().equals(order.toLowerCase()) ? jpaMovieRepository.getSortedMoviesByPriceAsc()
                                                                             : jpaMovieRepository.getSortedMoviesByPriceDesc();
  }

  public List<Movie> sortedByGenreId(Long id, MovieRequest movieRequest) {
    return jpaMovieRepository.sortMoviesByGenre(id, movieRequest);
  }

  public List<Movie> findRandomNumberOfMovies(int randomNumberOfMovies) {
    return jpaMovieRepository.findRandomNumberOfMovies(randomNumberOfMovies);
  }

  public Movie getMovieById(Long movieId) {
    return jpaMovieRepository.getById(movieId);
  }
}
