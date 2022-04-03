package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Poster;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends CrudRepository<Poster, Integer> {

  <S extends Poster> List<S> saveAll(Iterable<S> genres);
}
