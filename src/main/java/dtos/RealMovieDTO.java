/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.RealMovie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author peter
 */
public class RealMovieDTO {
    private Long id;
    private int year;
    private String title;
    private String[] actors;

    public RealMovieDTO(int year, String title, String[] actors) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public RealMovieDTO(RealMovie movie) {
        if (movie.getId() != null){ 
            this.id = movie.getId();
        }
        
        this.year = movie.getYear();
        this.title = movie.getTitle();
        this.actors = movie.getActors();
    }
    
    public static List<RealMovieDTO> getDtos(List<RealMovie> movies){
        List<RealMovieDTO> mvDtos = new ArrayList();
        movies.forEach(x->mvDtos.add(new RealMovieDTO(x)));
        return mvDtos;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RealMovieDTO{" + "id=" + id + ", year=" + year + ", title=" + title + ", actors=" + actors + '}';
    }
    
    
    
}