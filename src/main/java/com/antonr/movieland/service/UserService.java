package com.antonr.movieland.service;

import com.antonr.movieland.repository.jpa.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final JpaUserRepository jpaUserRepository;

}
