package com.example.moovienetwork.Dto;

import com.example.moovienetwork.Model.Category;
import com.example.moovienetwork.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private String year;

    private String runtime;

    private String director;

    private String actors;

    private String pilot;

    private String posterUrl;

    private Set<User> likes= new HashSet<>();

    private Set<Category> movieCategories = new HashSet<>();
}
