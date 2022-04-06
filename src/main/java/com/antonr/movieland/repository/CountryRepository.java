package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.entity.Genre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
