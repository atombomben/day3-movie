/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RealMovieDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import entities.*;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author peter
 */
public class RealMovieFacade {
    
    private static RealMovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private RealMovieFacade() {}
    
    public static RealMovieFacade getRealMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RealMovieFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<RealMovieDTO> getAll() {
        
        EntityManager em = getEntityManager();
        try {
        TypedQuery<RealMovie> q1 = em.createQuery("SELECT r FROM RealMovie r", RealMovie.class);
        List<RealMovie> realmovies = q1.getResultList();
            System.out.println(realmovies);
        return RealMovieDTO.getDtos(realmovies);
        } finally {
            em.close();
        }
    }
    
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(r) FROM RealMovie r").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
    }
    
     public RealMovieDTO getMovieById(long id){
        EntityManager em = emf.createEntityManager();
        return new RealMovieDTO(em.find(RealMovie.class, id));
    }
    
    public RealMovieDTO create(RealMovieDTO rmd){
        RealMovie rm = new RealMovie(rmd.getYear(), rmd.getTitle(), rmd.getActors());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rm);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        System.out.println(rm.toString());
        return new RealMovieDTO(rm);
    }
    
    public List<RealMovieDTO> getMovieByTitle (String title) {
        EntityManager em = getEntityManager();
        
        try {
        TypedQuery<RealMovie> q1 = em.createQuery("SELECT r FROM RealMovie r WHERE r.title = :title", RealMovie.class);
        q1.setParameter("title",title);
        List<RealMovie> movies = q1.getResultList();
        
        return RealMovieDTO.getDtos(movies);
        
        }finally {
            em.close();
        }
    }
    
    
}