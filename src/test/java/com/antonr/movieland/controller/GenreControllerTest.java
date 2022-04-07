package com.antonr.movieland.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.service.GenreService;
import java.util.Collections;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private GenreService genreService;

  @Test
  @SneakyThrows
  public void testGetAllGenres() {
    Genre drama = Genre.builder()
                       .id(1L)
                       .name("драма")
                       .movies(Collections.emptySet())
                       .build();
    Genre crime = Genre.builder()
                       .id(2L)
                       .name("криминал")
                       .movies(Collections.emptySet())
                       .build();

    when(genreService.findAll()).thenReturn(List.of(drama, crime));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genre")
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("драма"))
           .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
           .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("криминал"));
  }

}
