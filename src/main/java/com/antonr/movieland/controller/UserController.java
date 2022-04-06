package com.antonr.movieland.controller;

import com.antonr.movieland.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

  private final UserService userService;

}
