package com.example.moovienetwork.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String year;

    @Column
    private String runtime;

    @Column
    private String director;

    @Column
    private String actors;

    @Column
    private String pilot;

    @Column
    private String posterUrl;

    @ManyToMany(mappedBy = "likedMovies")
    private Set<User> likes= new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> movieCategories = new HashSet<>();
}
