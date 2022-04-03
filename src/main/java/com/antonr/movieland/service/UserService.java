package com.antonr.movieland.service;

import com.antonr.movieland.entity.User;
import com.antonr.movieland.repository.UserRepository;
import io.vavr.Tuple2;
import io.vavr.collection.Seq;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private static final int INDEX_OF_FIRST_ELEMENT = 0;
  private static final int AMOUNT_OF_USER_ELEMENTS = 3;

  private final UserRepository posterRepository;

  public List<User> saveAll(List<User> genres) {
    return posterRepository.saveAll(genres);
  }

  public List<User> getAllUsersFromFile(List<String> allLines) {
    Seq<Tuple2<String, Integer>> linesWithIndexes = io.vavr.collection.List.ofAll(allLines)
                                                                           .zipWithIndex();

    return setAllUserDependencies(linesWithIndexes, new ArrayList<>());
  }

  private List<User> setAllUserDependencies(Seq<Tuple2<String, Integer>> linesWithIndexes,
                                            List<User> users) {
    if (linesWithIndexes.isEmpty()) {
      return users;
    }
    Tuple2<String, Integer> lineWithIndex = linesWithIndexes.get(INDEX_OF_FIRST_ELEMENT);
    if (lineWithIndex._2() % AMOUNT_OF_USER_ELEMENTS == 0) {
      String[] userName = lineWithIndex._1().split(" ");
      users.add(User.builder()
                    .name(userName[0].trim())
                    .lastname(userName[1].trim())
                    .build());
    } else if (lineWithIndex._2() % AMOUNT_OF_USER_ELEMENTS == 1) {
      users.get(users.size() - 1).setLogin(lineWithIndex._1().trim());
    } else if (lineWithIndex._2() % AMOUNT_OF_USER_ELEMENTS == 2) {
      users.get(users.size() - 1).setPassword(lineWithIndex._1().trim());
    }
    return setAllUserDependencies(linesWithIndexes.subSequence(1), users);
  }
}
