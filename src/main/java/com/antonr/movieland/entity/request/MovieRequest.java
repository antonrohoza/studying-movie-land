package com.antonr.movieland.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MovieRequest {

  private final SortField sortField;
  private final SortDirection sortDirection;
}
