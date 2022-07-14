package com.example.moovienetwork.Service;


import com.example.moovienetwork.Dto.CommentDto;
import com.example.moovienetwork.Dto.MovieDto;
import com.example.moovienetwork.Model.Category;
import com.example.moovienetwork.Model.Comment;
import com.example.moovienetwork.Model.Enums.Role;
import com.example.moovienetwork.Model.Enums.UserType;
import com.example.moovienetwork.Model.Movie;
import com.example.moovienetwork.Model.User;
import com.example.moovienetwork.Repository.CommentRepository;
import com.example.moovienetwork.Repository.MovieRepository;
import com.example.moovienetwork.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    final MovieRepository movieRepository;

    final UserRepository userRepository;

    final CommentRepository commentRepository;

    final ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, UserRepository userRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public MovieDto createMovie(MovieDto movieDto) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            Movie movie = modelMapper.map(movieDto, Movie.class);

            return modelMapper.map(movieRepository.save(movie), MovieDto.class);
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public List<MovieDto> createAll(List<MovieDto> movieDtos) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            List<Movie> movies = movieDtos.stream().map(movieDto -> modelMapper.map(movieDto, Movie.class)).toList();
            movieRepository.saveAll(movies);

            return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
    }

    public MovieDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        return modelMapper.map(movie, MovieDto.class);
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto) throws AccessDeniedException {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        movie.setTitle(movieDto.getTitle());
        movie.setYear(movieDto.getYear());
        movie.setRuntime(movieDto.getRuntime());
        movie.setDirector(movieDto.getDirector());
        movie.setActors(movieDto.getActors());
        movie.setPlot(movieDto.getPlot());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setGenres(movieDto.getGenres().stream().map(genre -> modelMapper.map(genre, Category.class)).collect(Collectors.toSet()));

        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDto.class);
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");
    }

    public String deleteMovie(Long id) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
        movieRepository.deleteById(id);

        return id + " id'li film silindi.";
    } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");
    }

    public MovieDto addComment(Long movieId, CommentDto commentDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userRepository.findByEmail(email);
        if(foundUser.getUserType() != UserType.FREE){
            Movie movie = movieRepository.findById(movieId).orElseThrow();
            Comment comment = modelMapper.map(commentDto, Comment.class);
            comment.setUser(foundUser);
            comment.setMovie(movie);
            commentRepository.save(comment);


            return modelMapper.map(movie, MovieDto.class);
        } else throw new RuntimeException("Yorum yapabilmeniz için premium üye olmanız gerekli.");


    }
}
