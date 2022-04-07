package com.antonr.movieland.service;

import com.antonr.movieland.entity.dto.Currency;
import com.antonr.movieland.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
  ObjectMapper objectMapper = new ObjectMapper();

  @SneakyThrows
  public double getCurrencyRate(String currencyCode) {
    URL url = new URL(Constants.NBU_LINK_OF_CURRENCIES);
    List<Currency> allCurrencies = Arrays.asList(objectMapper.readValue(url, Currency[].class));
    return allCurrencies.stream()
                        .filter(c -> Objects.equals(c.getCc(), currencyCode.toUpperCase()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("There is no suitable such currency codes"))
                        .getRate();
  }
}
