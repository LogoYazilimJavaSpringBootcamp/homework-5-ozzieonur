package com.example.moovienetwork.Dto;

import com.example.moovienetwork.Model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;

    private String genre;

    private Set<Movie> movies = new HashSet<>();
}
