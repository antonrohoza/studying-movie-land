package com.antonr.movieland.entity.dto;

import com.antonr.movieland.entity.Movie;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertorDto {

  public List<MovieWithPicturePath> movieDto(List<Movie> movies) {

    return movies.stream()
                 .map(movie -> MovieWithPicturePath.builder()
                                                   .movieId(movie.getId())
                                                   .nameRussian(movie.getNameRussian())
                                                   .nameNative(movie.getNameNative())
                                                   .yearOfRelease(movie.getYearOfRelease())
                                                   .rating(movie.getRating())
                                                   .price(movie.getPrice())
                                                   .picturePath(movie.getPicturePath())
                                                   .build())
                 .collect(Collectors.toList());
  }


}
