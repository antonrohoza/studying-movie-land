package com.antonr.movieland.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "genre")
@NoArgsConstructor
public class Genre implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long genre_id;
  private String genre;

  @ManyToMany(mappedBy = "genres")
  private Set<Movie> movies;

  public Genre(String genre){
    this.genre = genre;
  }
}
