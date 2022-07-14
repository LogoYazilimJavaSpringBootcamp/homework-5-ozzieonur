package com.example.moovienetwork.Dto;

import com.example.moovienetwork.Model.Enums.Role;
import com.example.moovienetwork.Model.Enums.UserType;
import com.example.moovienetwork.Model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


public class UserDto {


    private Long id;

    private String fullName;

    private String email;

    private String password;

    private Role role;

    private UserType userType;

    private Set<UserMovieDto> likedMovies = new HashSet<>();

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<UserMovieDto> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(Set<UserMovieDto> likedMovies) {
        this.likedMovies = likedMovies;
    }
}
