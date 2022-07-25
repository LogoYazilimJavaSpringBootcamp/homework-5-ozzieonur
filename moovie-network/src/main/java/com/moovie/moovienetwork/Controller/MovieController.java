package com.moovie.moovienetwork.Controller;

import com.moovie.moovienetwork.Dto.CommentDto;
import com.moovie.moovienetwork.Dto.MovieDto;
import com.moovie.moovienetwork.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable Long id){
        return movieService.getMovie(id);
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody MovieDto movieDto) throws AccessDeniedException {
       return movieService.createMovie(movieDto);
    }

    @PostMapping("/saveAll")
    public List<MovieDto> createAllMovies(@RequestBody List<MovieDto> movieDtos) throws AccessDeniedException{
        return movieService.createAll(movieDtos);
    }

    @PutMapping("/{id}")
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieDto movieDto) throws AccessDeniedException{
        return movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie (@PathVariable Long id) throws AccessDeniedException{
        return movieService.deleteMovie(id);
    }

    @PostMapping("/{movieId}/comments")
    public MovieDto addComment(@PathVariable Long movieId, @RequestBody CommentDto commentDto){
        return movieService.addComment(movieId,commentDto);
    }
}
