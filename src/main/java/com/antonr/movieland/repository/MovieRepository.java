package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

  @Query("FROM Movie m ORDER BY m.rating ASC")
  List<Movie> getSortedMoviesByRatingAsc();

  @Query("FROM Movie m ORDER BY m.rating DESC")
  List<Movie> getSortedMoviesByRatingDesc();

  @Query("FROM Movie m ORDER BY m.price ASC")
  List<Movie> getSortedMoviesByPriceAsc();

  @Query("FROM Movie m ORDER BY m.price DESC")
  List<Movie> getSortedMoviesByPriceDesc();

}
