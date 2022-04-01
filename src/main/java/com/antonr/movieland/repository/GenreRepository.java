package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Genre;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

  List<Genre> findAll();
}
