package com.antonr.movieland.repository;

import com.antonr.movieland.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  <S extends User> List<S> saveAll(Iterable<S> genres);
}
