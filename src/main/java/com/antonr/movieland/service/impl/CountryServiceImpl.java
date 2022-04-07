package com.antonr.movieland.service.impl;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.repository.jpa.JpaCountryRepository;
import com.antonr.movieland.service.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final JpaCountryRepository jpaCountryRepository;

  public List<Country> findAll() {
    return jpaCountryRepository.findAll();
  }

}
