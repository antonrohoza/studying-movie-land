package com.antonr.movieland.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name_ru;
  private String name_en;
  private int year;
  private String description;
  private double rating;
  private double price;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "movie_country",
             joinColumns = {@JoinColumn(name = "movie_id")},
             inverseJoinColumns = {@JoinColumn(name = "country_id")})
  private Set<Country> countries;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "movie_genre",
             joinColumns = {@JoinColumn(name = "movie_id")},
             inverseJoinColumns = {@JoinColumn(name = "genre_id")})
  private Set<Genre> genres;

  @OneToMany
  private Set<Review> reviews;

}
