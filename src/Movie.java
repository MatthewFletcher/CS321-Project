import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

class Movie implements Serializable{

    private String m_title;
    private Integer m_year;
    private String m_director;
    private ArrayList<String> m_actors;
    private Double m_rating;
    private String m_genre;


    //Default constructor
    public Movie()
    {
    }

    //Overloaded constructor
    public Movie(String title, Integer year, String director, ArrayList<String> actors, Double rating, String genre)
    {
        m_title = title;
        m_year = year;
        m_director = director;
        m_actors = new ArrayList<String>();
        m_rating = rating;
        m_genre = genre;

        for (String actor: actors)
        {
            m_actors.add(actor);
        }
    }

    public String toString()
    {

        String actors = "\n";
        for (String actor: m_actors)
        {
            actors += String.format("%s\n", actor);
        }

        return String.format("Title: %s\nYear: %d\nDirector: %s\nActors:%s\nRating: %.1f/5, Genre: %s", m_title, m_year, m_director, actors, m_rating, m_genre);
    }

    public  JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("title",m_title) ;
            obj.put("director",m_director) ;
            obj.put("year",m_year) ;
            
        }

        catch (Exception e) {e.printStackTrace();}

        return obj;
    }

    public String getTitle()
    {
        return m_title;
    }

    public Integer getYear()
    {
        return m_year;
    }

    public String getDirector()
    {
        return m_director;
    }

    public ArrayList<String> getActors()
    {
        return m_actors;
    }

    public Double getRating()
    {
        return m_rating;
    }

    public String getGenre()
    {
        return m_genre;
    }

}
