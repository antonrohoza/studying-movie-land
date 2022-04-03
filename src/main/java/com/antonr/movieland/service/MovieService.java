package com.antonr.movieland.service;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.repository.MovieRepository;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService {

  private static final String IDENTIFIER_OF_MOVIE = "/";
  private static final int INDEX_OF_FIST_ELEMENT = 0;
  private static final int AMOUNT_OF_MOVIE_ELEMENTS = 7;

  MovieRepository movieRepository;

  public Pair<List<Movie>, Set<Country>> generateMoviesWithCountries(List<String> allLines) {
    Seq<Tuple2<String, Integer>> linesWithIndexes = io.vavr.collection.List.ofAll(allLines)
                                                                           .zipWithIndex();

    return setMovieAndCountryDependencies(linesWithIndexes, new ArrayList<>(), new HashSet<>());
  }

  private Pair<List<Movie>, Set<Country>> setMovieAndCountryDependencies(
      Seq<Tuple2<String, Integer>> linesWithIndexes, List<Movie> movies, Set<Country> countries) {
    if (linesWithIndexes.isEmpty()) {
      return Pair.of(movies, countries);
    }
    Tuple2<String, Integer> lineWithIndex = linesWithIndexes.get(INDEX_OF_FIST_ELEMENT);
    if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 0) {
      setMovieName(movies, lineWithIndex._1());
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 1) {
      movies.get(movies.size() - 1).setYear(Integer.parseInt(lineWithIndex._1()));
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 2) {
      setCountries(movies, countries, lineWithIndex);
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 3) {
      setGenres(movies, lineWithIndex);
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 4) {
      Movie lastMovie = movies.get(movies.size() - 1);
      lastMovie.setDescription(
          lastMovie.getDescription() != null ? lastMovie.getDescription() + lineWithIndex._1()
                                             : lineWithIndex._1());
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 5) {
      String ratingValue = lineWithIndex._1().split(":")[1].trim();
      movies.get(movies.size() - 1).setRating(Double.parseDouble(ratingValue));
    } else if (lineWithIndex._2() % AMOUNT_OF_MOVIE_ELEMENTS == 6) {
      String priceValue = lineWithIndex._1().split(":")[1].trim();
      movies.get(movies.size() - 1).setPrice(Double.parseDouble(priceValue));
    }
    return setMovieAndCountryDependencies(linesWithIndexes.subSequence(1), movies, countries);
  }

  private void setGenres(List<Movie> movies, Tuple2<String, Integer> lineWithIndex) {
    Set<Genre> currentMovieGenres = Arrays.stream(lineWithIndex._1().split(","))
                                          .map(String::trim)
                                          .map(genreName -> Genre.builder()
                                                                 .name(genreName)
                                                                 .build())
                                          .collect(Collectors.toSet());
    movies.get(movies.size() - 1).setGenres(currentMovieGenres);
  }

  private void setCountries(List<Movie> movies, Set<Country> countries,
                            Tuple2<String, Integer> lineWithIndex) {
    Set<Country> currentMovieCountries = Arrays.stream(lineWithIndex._1().split(","))
                                               .map(String::trim)
                                               .map(countryName -> Country.builder()
                                                                          .name(countryName)
                                                                          .build())
                                               .collect(Collectors.toSet());
    countries.addAll(currentMovieCountries);
    movies.get(movies.size() - 1).setCountries(currentMovieCountries);
  }

  private void setMovieName(List<Movie> movies, String line) {
    String[] names = line.split(IDENTIFIER_OF_MOVIE);
    movies.add(Movie.builder()
                    .name_ru(names[0])
                    .name_en(names[1])
                    .build());
  }

}
