package com.antonr.movieland.repository.impl;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.entity.Genre_;
import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.entity.request.MovieRequest;
import com.antonr.movieland.entity.request.SortDirection;
import com.antonr.movieland.repository.MovieRepository;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

  EntityManager entityManager;

  public List<Movie> findRandomNumberOfMovies(int randomNumber) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> criteriaQueryCount = criteriaBuilder.createQuery(Long.class);
    Root<Movie> movieCountRoot = criteriaQueryCount.from(Movie.class);

    int count = entityManager.createQuery(criteriaQueryCount.select(criteriaBuilder.count(movieCountRoot)))
                             .getSingleResult()
                             .intValue();
    // +1 because upper bound is exclusive in nextInt()
    int index = new Random().nextInt(count - randomNumber + 1);
    CriteriaQuery<Movie> criteriaQueryUser = criteriaBuilder.createQuery(Movie.class);
    Root<Movie> userRoot = criteriaQueryUser.from(Movie.class);

    return entityManager.createQuery(criteriaQueryUser.select(userRoot).orderBy())
                        .setFirstResult(index)
                        .setMaxResults(randomNumber)
                        .getResultList();
  }

  public List<Movie> sortMoviesByGenre(Long genreId, MovieRequest movieRequest) {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Movie> criteriaMovie = criteriaBuilder.createQuery(Movie.class);
    Root<Genre> genreRoot = criteriaMovie.from(Genre.class);
    SetJoin<Genre, Movie> movies = genreRoot.join(Genre_.movies);
    criteriaMovie.where(criteriaBuilder.equal(genreRoot.get(Genre_.id), genreId));

    String sortFieldName = movieRequest.getSortField().getName();

    return movieRequest.getSortDirection() == SortDirection.ASC ? getSortedMovieList(criteriaMovie, movies, criteriaBuilder.asc(movies.get(sortFieldName)))
                                                                : getSortedMovieList(criteriaMovie, movies, criteriaBuilder.desc(movies.get(sortFieldName)));
  }

  private List<Movie> getSortedMovieList(CriteriaQuery<Movie> criteriaMovie, SetJoin<Genre, Movie> movies, Order order) {
    return entityManager.createQuery(criteriaMovie.select(movies).orderBy(order))
                        .getResultList();
  }

}
