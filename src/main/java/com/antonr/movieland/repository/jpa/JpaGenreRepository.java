package com.antonr.movieland.repository.jpa;

import com.antonr.movieland.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Long> {

}
