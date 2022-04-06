package com.antonr.movieland.repository.jpa;

import com.antonr.movieland.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCountryRepository extends JpaRepository<Country, Long> {

}
