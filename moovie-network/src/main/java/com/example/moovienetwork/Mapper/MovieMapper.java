package com.example.moovienetwork.Mapper;


import com.example.moovienetwork.Dto.MovieDto;
import com.example.moovienetwork.Model.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface MovieMapper {

    Movie dtoToEntity(MovieDto movieDto);
    MovieDto entityToDto(Movie movie);

    List<Movie> dtoToEntity(List<MovieDto> movieDtos);
    List<MovieDto> entityToDto(List<Movie> movies);
}
