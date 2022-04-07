package com.antonr.movieland.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.antonr.movieland.entity.Genre;
import com.antonr.movieland.entity.Movie;
import com.antonr.movieland.service.GenreService;
import com.antonr.movieland.service.MovieService;
import com.antonr.movieland.utils.Constants;
import java.util.List;
import java.util.Set;
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
  @MockBean
  private MovieService movieService;
  @MockBean
  private GenreService genreService;

  private final Movie showshenk = Movie.builder()
                                       .id(1L)
                                       .nameNative("Showshank")
                                       .yearOfRelease(1997)
                                       .description("Showshenk Redemption")
                                       .rating(9.8)
                                       .price(123.4)
                                       .picturePath("http://pictureShowshank")
                                       .build();
  private final Movie greenMile = Movie.builder()
                                       .id(2L)
                                       .nameNative("GreenMile")
                                       .yearOfRelease(1997)
                                       .description("Policeman in the prison")
                                       .rating(9.9)
                                       .price(132.4)
                                       .picturePath("http://greenMile")
                                       .build();
  private final Movie forestGump = Movie.builder()
                                        .id(3L)
                                        .nameNative("ForestGump")
                                        .yearOfRelease(1998)
                                        .description("Mentally retarded person")
                                        .rating(9.7)
                                        .price(140.23)
                                        .picturePath("http://forestGump")
                                        .build();

  @Test
  @SneakyThrows
  public void testGetMovieById(){

    when(movieService.getMovieById(1L)).thenReturn(showshenk);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
           .andExpect(MockMvcResultMatchers.jsonPath("$.nameNative").value("Showshank"));

  }

  @Test
  @SneakyThrows
  public void testGetMovieByIdWithCurrencyUSD(){

    when(movieService.getMovieByIdWithCurrency(1L, "USD")).thenReturn(greenMile);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}?currency=USD", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2));
  }

  @Test
  @SneakyThrows
  public void testGetMovieByIdWithCurrencyEUR(){

    when(movieService.getMovieByIdWithCurrency(1L, "EUR")).thenReturn(forestGump);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{movieId}?currency=EUR", 1)
                                          .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
  }


  @Test
  @SneakyThrows
  public void testGetMovieByGenreId() {
    Genre genre = Genre.builder().id(1L)
                       .name("драма")
                       .movies(Set.of(greenMile, forestGump)).build();
    when(genreService.getById(1L)).thenReturn(genre);
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/genre/{genreId}", 1)
                                                        .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(2))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameNative").value("GreenMile"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(3))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameNative").value("ForestGump"));
  }

  @Test
  @SneakyThrows
  public void testGetRandomMovie(){
    when(movieService.getRandomNumberOfMovies(Constants.RANDOM_NUMBER_OF_MOVIES)).thenReturn(List.of(showshenk, greenMile, forestGump));
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/random")
                                                                        .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(1))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameNative").value("Showshank"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(2))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameNative").value("GreenMile"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[2].movieId").value(3))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nameNative").value("ForestGump"));
  }

  @Test
  @SneakyThrows
  public void testFindAllMovies(){
    when(movieService.findAll()).thenReturn(List.of(showshenk, greenMile, forestGump));
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                                                                        .accept(MediaType.APPLICATION_JSON));

    resultActions.andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].movieId").value(1))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameNative").value("Showshank"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].movieId").value(2))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[1].nameNative").value("GreenMile"))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[2].movieId").value(3))
                 .andExpect(MockMvcResultMatchers.jsonPath("$[2].nameNative").value("ForestGump"));
  }

}
