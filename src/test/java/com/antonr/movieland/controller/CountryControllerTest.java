package com.antonr.movieland.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.antonr.movieland.entity.Country;
import com.antonr.movieland.service.CountryService;
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

@WebMvcTest(CountryController.class)
class CountryControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CountryService countryService;

  @Test
  @SneakyThrows
  public void testGetAllGenres() {
    Country usa = Country.builder()
                         .id(1L)
                         .name("США")
                         .movies(Collections.emptyList())
                         .build();
    Country france = Country.builder()
                            .id(2L)
                            .name("Франция")
                            .movies(Collections.emptyList())
                            .build();

    when(countryService.findAll()).thenReturn(List.of(usa, france));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/country")
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("США"))
           .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
           .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Франция"));
  }

}
