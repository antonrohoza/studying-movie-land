package com.antonr.movieland.service.impl;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.request.MovieRequest;
import com.antonr.movieland.entity.request.SortDirection;
import com.antonr.movieland.repository.jpa.JpaMovieRepository;
import com.antonr.movieland.service.CurrencyService;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final JpaMovieRepository jpaMovieRepository;
  private final CurrencyService currencyService;

  public List<Movie> findAll() {
    return jpaMovieRepository.findAll();
  }

  public List<Movie> sortedMoviesByRating(String order) {
    return SortDirection.ASC.getDirectionOrder().equalsIgnoreCase(order) ? jpaMovieRepository.getSortedMoviesByRatingAsc()
                                                                         : jpaMovieRepository.getSortedMoviesByRatingDesc();
  }

  public List<Movie> sortedMoviesByPrice(String order) {
    return SortDirection.ASC.getDirectionOrder().equalsIgnoreCase(order) ? jpaMovieRepository.getSortedMoviesByPriceAsc()
                                                                         : jpaMovieRepository.getSortedMoviesByPriceDesc();
  }

  public List<Movie> sortedByGenreId(Long id, MovieRequest movieRequest) {
    return jpaMovieRepository.sortMoviesByGenre(id, movieRequest);
  }

  public List<Movie> getRandomNumberOfMovies(int randomNumberOfMovies) {
    return jpaMovieRepository.findRandomNumberOfMovies(randomNumberOfMovies);
  }

  public Movie getMovieById(Long movieId) {
    return jpaMovieRepository.getById(movieId);
  }

  public Movie getMovieByIdWithCurrency(Long movieId, String currency) {
    Movie movie = jpaMovieRepository.getById(movieId);
    double priceInCurrency = BigDecimal.valueOf(movie.getPrice() / currencyService.getCurrencyRate(currency))
                                       .setScale(Constants.PLACES_AFTER_COMA, RoundingMode.HALF_UP)
                                       .doubleValue();
    movie.setPrice(priceInCurrency);
    return movie;
  }
}
