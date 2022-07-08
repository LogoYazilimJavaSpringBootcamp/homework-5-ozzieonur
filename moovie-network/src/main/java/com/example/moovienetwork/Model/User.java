package com.example.moovienetwork.Model;

import com.example.moovienetwork.Model.Enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 32, columnDefinition = "varchar(32) default 'FREE'")
    private UserType userType;


    @ManyToMany
    @JoinTable(
            name = "user_movies",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<Movie> likedMovies = new HashSet<>();

}
