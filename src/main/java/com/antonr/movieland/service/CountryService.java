package com.antonr.movieland.service;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.repository.jpa.JpaCountryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {

  private final JpaCountryRepository jpaCountryRepository;

  public List<Country> findAll() {
    return jpaCountryRepository.findAll();
  }

}
