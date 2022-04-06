package com.antonr.movieland.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortField {
  PRICE("price"),
  RATING("rating");

  private final String name;
}
