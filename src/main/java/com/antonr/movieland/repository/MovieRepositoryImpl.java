package com.antonr.movieland.repository;

import com.antonr.movieland.entity.Movie;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MovieRepositoryImpl {

  EntityManager em;

  public List<Movie> findRandomNumberOfMovies(int randomNumber) {
    CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cqCount = qb.createQuery(Long.class);
    Root<Movie> movieCountRoot = cqCount.from(Movie.class);

    int count = em.createQuery(cqCount.select(qb.count(movieCountRoot)))
                  .getSingleResult()
                  .intValue();
    int index = new Random().nextInt(count - randomNumber);
    CriteriaQuery<Movie> cqUser = qb.createQuery(Movie.class);
    Root<Movie> userRoot = cqUser.from(Movie.class);

    return em.createQuery(cqUser.select(userRoot))
             .setFirstResult(index)
             .setMaxResults(randomNumber)
             .getResultList();
  }

}
