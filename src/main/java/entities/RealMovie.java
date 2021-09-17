package entities;

import java.io.Serializable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 *
 * @author peter
 */
@Entity
@NamedQueries({
@NamedQuery(name = "RealMovie.deleteAllRows", query = "DELETE from RealMovie"),
@NamedQuery(name = "RealMovie.getAll", query = "SELECT m FROM RealMovie m"),
@NamedQuery(name = "RealMovie.getByTitle", query = "SELECT m FROM RealMovie m WHERE m.title LIKE :title")
})
public class RealMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private String title;
    private String[] actors;

    public RealMovie() {
    }

    @Override
    public String toString() {
        return "RealMovie{" + "id=" + id + ", year=" + year + ", title=" + title + ", actors=" + actors + '}';
    }

    
    
    public RealMovie(int year, String title, String[] actors) {
        this.year = year;
        this.title = title;
        this.actors = actors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
  
    

}
