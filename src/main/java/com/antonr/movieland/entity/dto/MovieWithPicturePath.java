package com.antonr.movieland.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieWithPicturePath {

  private Long movieId;
  private String nameRussian;
  private String nameNative;
  private int yearOfRelease;
  private double rating;
  private double price;
  private String picturePath;
}
