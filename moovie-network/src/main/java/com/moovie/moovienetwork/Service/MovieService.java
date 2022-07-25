package com.moovie.moovienetwork.Service;


import com.moovie.moovienetwork.Dto.CommentDto;
import com.moovie.moovienetwork.Dto.EmailDto;
import com.moovie.moovienetwork.Dto.MovieDto;
import com.moovie.moovienetwork.Model.Category;
import com.moovie.moovienetwork.Model.Comment;
import com.moovie.moovienetwork.Model.Enums.Role;
import com.moovie.moovienetwork.Model.Enums.UserType;
import com.moovie.moovienetwork.Model.Movie;
import com.moovie.moovienetwork.Model.User;
import com.moovie.moovienetwork.Repository.CommentRepository;
import com.moovie.moovienetwork.Repository.MovieRepository;
import com.moovie.moovienetwork.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    final MovieRepository movieRepository;

    final UserRepository userRepository;

    private final AmqpTemplate rabbitTemplate;

    final CommentRepository commentRepository;

    final ModelMapper modelMapper;

    public MovieService(MovieRepository movieRepository, UserRepository userRepository, CommentRepository commentRepository, ModelMapper modelMapper, AmqpTemplate rabbitTemplate) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    public MovieDto createMovie(MovieDto movieDto) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            Movie movie = modelMapper.map(movieDto, Movie.class);


            List<User> users =  userRepository.findAll();

            for (User user: users){
                rabbitTemplate.convertAndSend("moovie-network.email",
                        new EmailDto(user.getEmail(),
                        "Moovie'e yeni film eklendi" ,
                        movieDto.getTitle() + " filmi sisteme eklendi. İzlemeyi düşünür müsün ?"));
                log.info("Email servisine gönderildi: " + user.getEmail());

            }
                    log.info(movieDto.getTitle() +  " filmi sisteme eklendi.");
            return modelMapper.map(movieRepository.save(movie), MovieDto.class);




        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public List<MovieDto> createAll(List<MovieDto> movieDtos) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
            List<Movie> movies = movieDtos.stream().map(movieDto -> modelMapper.map(movieDto, Movie.class)).toList();
            movieRepository.saveAll(movies);

            log.info("Filmler sisteme eklendi.");

            return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");

    }

    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        log.info("Tüm filmler görüntülendi.");

        return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
    }

    public MovieDto getMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();

        log.info(id + " id'li film görüntülendi.");

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

        log.info(movieDto.getTitle() + " filmi güncellendi.");

        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDto.class);
        } else throw new AccessDeniedException("Erişim iznine sahip değilsiniz");
    }

    public String deleteMovie(Long id) throws AccessDeniedException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authUser.getRole() == Role.ADMIN) {
        movieRepository.deleteById(id);

        log.info(id + " id'li film sistemden silindi.");

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

            log.info(foundUser.getFullName() + " kullanıcısı " + movie.getTitle() + " filmine " + commentDto.getComment() + " yorumunu yaptı.");


            return modelMapper.map(movie, MovieDto.class);
        } else throw new RuntimeException("Yorum yapabilmeniz için premium üye olmanız gerekli.");


    }
}
