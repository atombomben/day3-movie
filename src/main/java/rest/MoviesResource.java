/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RealMovieDTO;
import facades.RealMovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author peter
 */
@Path("movies")
public class MoviesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MoviesResource
     */
    public MoviesResource() {
    }
    
    private final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private final RealMovieFacade FACADE =  RealMovieFacade.getRealMovieFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Retrieves representation of an instance of rest.MoviesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloString() {
        
        return "{\"msg\":\"Welcome to the Movielist!\"}";
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllMovies() {
        List<RealMovieDTO> rmd = FACADE.getAll();
        return gson.toJson(rmd);
    }
    
    @Path("count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMovieCount(){
        return "{\"count\":"+FACADE.getCount()+"}";
    }
     
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public String getMovieById(@PathParam("id") int id) {
        RealMovieDTO rmd = FACADE.getMovieById(id);
        return gson.toJson(rmd);
    }
   
    //Copied from Mikkel
    @Path("title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public Response getMovieByTitle(@PathParam("title") String title){
        List<RealMovieDTO> dto = FACADE.getMovieByTitle(title);
        
        if (dto == null){
            return Response.status(Response.Status.NOT_FOUND).entity("Movie not found").build();
        }
        return Response.ok(gson.toJson(dto), MediaType.APPLICATION_JSON).build();
    }
    
    //Copied from Mikkel
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addMovie (String movie){
        RealMovieDTO rmvDTO = gson.fromJson(movie, RealMovieDTO.class);
        rmvDTO = FACADE.create(rmvDTO);
        return gson.toJson(rmvDTO);
    }    
}
