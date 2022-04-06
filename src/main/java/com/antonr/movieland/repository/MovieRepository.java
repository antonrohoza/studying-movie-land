package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.request.MovieRequest;
import java.util.List;

public interface MovieRepository {

  List<Movie> findRandomNumberOfMovies(int randomNumber);

  List<Movie> sortMoviesByGenre(Long genreId, MovieRequest movieRequest);

}
