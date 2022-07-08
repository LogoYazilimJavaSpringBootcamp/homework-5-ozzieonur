package com.example.moovienetwork.Dto;

import com.example.moovienetwork.Model.Enums.UserType;
import com.example.moovienetwork.Model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    private String fullName;

    private String email;

    private String password;

    private UserType userType;

    private Set<Movie> likedMovies = new HashSet<>();
}
