package com.antonr.movieland.entity.request;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SortDirection {
  ASC("asc"),
  DESC("desc");

  private final String directionOrder;

  public static SortDirection findDirectionByOrder(String order){
    return Arrays.stream(SortDirection.values())
                 .filter(sd -> sd.getDirectionOrder().equals(order.toLowerCase()))
                 .findFirst()
                 .orElseThrow(() -> new RuntimeException("There is no such direction with value: " + order));
  }
}
