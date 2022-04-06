package com.antonr.movieland.repository.jpa;

import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.repository.MovieRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMovieRepository extends JpaRepository<Movie, Long>,
                                            MovieRepository {

  @Query("FROM Movie m ORDER BY m.rating ASC")
  List<Movie> getSortedMoviesByRatingAsc();

  @Query("FROM Movie m ORDER BY m.rating DESC")
  List<Movie> getSortedMoviesByRatingDesc();

  @Query("FROM Movie m ORDER BY m.price ASC")
  List<Movie> getSortedMoviesByPriceAsc();

  @Query("FROM Movie m ORDER BY m.price DESC")
  List<Movie> getSortedMoviesByPriceDesc();

}
