package com.antonr.movieland.controller;

import static com.antonr.movieland.utils.FileReader.getAllLinesFromFileByURL;

import com.antonr.movieland.entity.User;
import com.antonr.movieland.service.UserService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/user/add")
  public List<User> addUSER() {
    return userService.saveAll(userService.getAllUsersFromFile(getAllLinesFromFileByURL(Constants.USER_FILE)));
  }
}
