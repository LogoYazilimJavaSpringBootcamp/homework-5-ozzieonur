package com.moovie.moovienetwork.Dto;

import java.util.HashSet;
import java.util.Set;

public class UserMovieDto {

    private Long id;

    private String title;

    private String year;

    private String runtime;

    private String director;

    private String actors;

    private String plot;

    private String posterUrl;


    private Set<CategoryDto> genres = new HashSet<>();

    public UserMovieDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Set<CategoryDto> getGenres() {
        return genres;
    }

    public void setGenres(Set<CategoryDto> genres) {
        this.genres = genres;
    }
}
