package com.antonr.movieland.service;

import com.antonr.movieland.entity.Genre;
import java.util.List;

public interface GenreService {

  List<Genre> findAll();

  Genre getById(Long id);

}
