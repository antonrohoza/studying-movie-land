package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Movie;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

  List<Movie> findAll();
}
