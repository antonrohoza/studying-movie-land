package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.service.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/country")
public class CountryController {

  private final CountryService countryService;

  @GetMapping
  public List<Country> getAllCountries() {
    log.info("get all countries");
    return countryService.findAll();
  }

}
