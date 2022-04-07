package com.antonr.movieland.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.service.CurrencyService;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private MovieService movieService;

  @MockBean
  private CurrencyService currencyService;

  @MockBean
  private GenreService genreService;

  private final List<Movie> movieList;

  MovieControllerTest() {
    Movie movie1 = Movie.builder()
                        .id(1L)
                        .nameNative("Showshank")
                        .yearOfRelease(1997)
                        .description("Showshenk Redemption")
                        .rating(9.8)
                        .price(123.4)
                        .picturePath("http://pictureShowshank")
                        .build();
    Movie movie2 = Movie.builder()
                        .id(2L)
                        .nameNative("GreenMile")
                        .yearOfRelease(1997)
                        .description("Policeman in the prison")
                        .rating(9.9)
                        .price(132.4)
                        .picturePath("http://greenMile")
                        .build();
    movieList = List.of(movie1, movie2);
  }

  @Test
  @SneakyThrows
  public void testGetMovieById(){

    when(movieService.getMovieById(1L)).thenReturn(movieList.get(0));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$.nameNative").value("Showshank"));

  }

  @Test
  @SneakyThrows
  public void testGetMovieByIdWithCurrencyUSD(){

    when(movieService.getMovieByIdWithCurrency(1L, "USD")).thenReturn(movieList.get(0));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}?currency=USD", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
  }

  @Test
  @SneakyThrows
  public void testGetMovieByIdWithCurrencyEUR(){

    when(movieService.getMovieByIdWithCurrency(1L, "EUR")).thenReturn(movieList.get(0));
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}?currency=EUR", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
  }


  @Test
  @SneakyThrows
  public void testGetMovieByGenreId() {
    Genre genre = Genre.builder().id(1L)
                       .name("драма")
                       .movies(new HashSet<>(movieList)).build();
    when(genreService.getById(1L)).thenReturn(genre);
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/{genreId}", 1)
                                                        .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(2))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameNative").value("GreenMile"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(1))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameNative").value("Showshank"));
  }

  @Test
  @SneakyThrows
  public void testGetRandomMovie(){
    when(movieService.getRandomNumberOfMovies(Constants.RANDOM_NUMBER_OF_MOVIES)).thenReturn(movieList);
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/random")
                                                                        .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(2))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameNative").value("GreenMile"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(1))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameNative").value("Showshank"));
  }

}
