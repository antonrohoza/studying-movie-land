package com.antonr.movieland.service;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.repository.CountryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {

  private final CountryRepository countryRepository;

  public List<Country> findAll() {
    return countryRepository.findAll();
  }

}
