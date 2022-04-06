package com.antonr.movieland.controller;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.service.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/country")
public class CountryController {

  CountryService countryService;

  @GetMapping
  public List<Country> getAllCountries(){
    return countryService.findAll();
  }

}
