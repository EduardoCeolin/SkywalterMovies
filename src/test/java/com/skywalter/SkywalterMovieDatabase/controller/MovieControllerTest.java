package com.skywalter.SkywalterMovieDatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.skywalter.SkywalterMovieDatabase.dto.FormMovie;
import com.skywalter.SkywalterMovieDatabase.dto.MovieDTO;
import com.skywalter.SkywalterMovieDatabase.model.Movie;
import com.skywalter.SkywalterMovieDatabase.service.MovieService;
import com.skywalter.SkywalterMovieDatabase.utils.Counter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    public static final MediaType MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieServiceMock;

    private MockMvc mockMvc;
    private ObjectMapper obj = new ObjectMapper();
    private FormMovie formMovie;
    private Movie movie;
    private MovieDTO movieDTO;
    private String movieId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movieController = new MovieController(movieServiceMock);
        obj.registerModule(new JavaTimeModule());
        formMovie = new FormMovie("Teste", LocalDate.now(),"Synopsis of teste",8);
        movie = new Movie("TESTE", LocalDate.now(), "SINOPSE", 9);
        movieDTO = new MovieDTO(movie);
        movieId = movieDTO.getId().toString();
    }

    @Test
    public void saveMovieTest() throws Exception {
        String payload = obj.writeValueAsString(formMovie);
        when(movieServiceMock.saveMovie(any(FormMovie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies")
                .contentType(MEDIA_TYPE)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));

        verify(movieServiceMock, times(1)).saveMovie(any());
        verifyNoMoreInteractions(movieServiceMock);
    }

    @Test
    public void updateMovieTest() throws Exception {
        String payload = obj.writeValueAsString(formMovie);
        when(movieServiceMock.updateMovie(any(), any())).thenReturn(movie);

        mockMvc.perform(put("/movies/"+movieId)
                .contentType(MEDIA_TYPE)
                .content(payload))
                .andExpect(status().isNoContent());

        verify(movieServiceMock, times(1)).updateMovie(any(), any());
        verifyNoMoreInteractions(movieServiceMock);
    }

    @Test
    public void getAllMoviesTest() throws Exception {
        List<MovieDTO> listMovies = new ArrayList<>();
        listMovies.add(movieDTO);

        String payload = obj.writeValueAsString(formMovie);
        when(movieServiceMock.findAll()).thenReturn(listMovies);

        mockMvc.perform(get("/movies/")
                .contentType(MEDIA_TYPE)
                .content(payload))
                .andExpect(status().isOk());

        verify(movieServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(movieServiceMock);
    }

    @Test
    public void getMovieByMovieId() throws Exception {
        String payload = obj.writeValueAsString(formMovie);
        when(movieServiceMock.findById(any())).thenReturn(movieDTO);

        mockMvc.perform(get("/movies/"+movieId)
                .contentType(MEDIA_TYPE)
                .content(payload))
                .andExpect(status().isOk());

        verify(movieServiceMock, times(1)).findById(any());
        verifyNoMoreInteractions(movieServiceMock);
    }

    @Test
    public void getLetterMetricsTop10() throws Exception {
        List<Counter> listCounter = new ArrayList<>();

        String payload = obj.writeValueAsString(formMovie);
        when(movieServiceMock.letterMetricsTop10()).thenReturn(listCounter);

        mockMvc.perform(get("/movies/letter-metrics-top10")
                .contentType(MEDIA_TYPE)
                .content(payload))
                .andExpect(status().isOk());

        verify(movieServiceMock, times(1)).letterMetricsTop10();
        verifyNoMoreInteractions(movieServiceMock);
    }

}
