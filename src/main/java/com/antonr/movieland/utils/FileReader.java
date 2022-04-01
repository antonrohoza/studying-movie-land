package com.antonr.movieland.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileReader {
  private static final String EMPTY_LINE = "";

  @SneakyThrows
  public static List<String> getAllLinesFromFileByURL(String fileName) {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new URL(fileName).openStream()))) {
      return reader.lines()
                   .filter(line -> !line.equals(EMPTY_LINE))
                   .collect(Collectors.toList());
    }
  }
}
