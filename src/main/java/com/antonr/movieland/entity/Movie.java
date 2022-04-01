package com.antonr.movieland.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String nameRu;
  private String nameEn;
  private int year;
  private String county;
  private String description;
  private double rating;
  private double price;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(name = "movie_genre",
             joinColumns = {@JoinColumn(name = "movie_id")},
             inverseJoinColumns = {@JoinColumn(name = "genre_id")})
  private Set<Genre> genres;

}
