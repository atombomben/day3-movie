/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RealMovieDTO;
import dtos.RenameMeDTO;
import entities.Movie;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;
import entities.RealMovie;

/**
 *
 * @author tha
 */
public class Populator {
    
    
    
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade fe = MovieFacade.getFacadeExample(emf);
        EntityManagerFactory emf1 = EMF_Creator.createEntityManagerFactory();
        RealMovieFacade RMF = RealMovieFacade.getRealMovieFacade(emf1);
        fe.create(new RenameMeDTO(new Movie("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new Movie("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new Movie("First 3", "Last 3")));
        RMF.create(new RealMovieDTO(2000, "King Kong", new String[]{"Hans-Henrik", "Peter Andersen"}));
        RMF.create(new RealMovieDTO(2010, "BlaBla", new String[]{"Jens", "Hansen"}));
        RMF.create(new RealMovieDTO(2020, "Jurassic World", new String[]{"Someone", "Someone"}));
        System.out.println("Hej");
        RMF.getAll();
        System.out.println("START TEST");
        RMF.getMovieByTitle("Jurassic World");
        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
