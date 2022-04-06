package com.antonr.movieland.entity.request;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum SortField {
  PRICE("price"),
  RATING("rating");

  private final String name;

  public SortField findSortFieldByName(String name){
    return Arrays.stream(SortField.values())
                 .filter(sd -> sd.getName().equals(name.toLowerCase()))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("There is no such sort field with name: " + name));
  }
}
