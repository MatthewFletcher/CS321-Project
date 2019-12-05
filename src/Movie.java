import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


class Movie {

    private String m_title;
    private Integer m_year;
    private String m_director;
    private ArrayList<String> m_actors = new ArrayList<String>();
    private Double m_rating;
    private String m_genre;
    private Boolean m_onWatchList;


    //Default constructor
    public Movie()
    {
        m_onWatchList = false;

    }

    /**
     * Constructor to create movie from a json object
     * @param JSON object with movie information
     * @return n/a
     */
    public Movie(JSONObject j)
    {
        m_title = (String) j.get("title");
        m_director = (String) j.get("director");
        Long o = (Long) j.get("year");
        m_year  = o.intValue();
        JSONArray actorList = new JSONArray();
        actorList =  (JSONArray) j.get("ActorList");

        for (Object j_actor: actorList)
        {
            m_actors.add((String)j_actor);
        }

        m_genre = (String) j.get("genre");
        m_rating = (Double) j.get("rating");
        m_onWatchList = (Boolean) j.get("onWatchList");

    }
    /**
     * Tester function to create a movie from keyboard input
     * @param None
     * @return a movie object created from keyboard input
     */
    public static Movie createMovie()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Creating Movie");


        System.out.print("Enter Title: ");
        String title = reader.nextLine();

        System.out.print("Enter year: ");
        int year = reader.nextInt();
        reader.nextLine(); //Consume \n character

        System.out.print("Enter Director: ");
        String director = reader.nextLine();


        System.out.print("Enter number of actors: ");
        int numactors  = reader.nextInt();
        reader.nextLine(); //Consume \n character

        ArrayList<String> actors = new ArrayList<String>();
        for (int i=0;i<numactors;i++)
        {
            System.out.printf("Enter actor %d: ", i+1);
            actors.add(reader.nextLine());
        }

        System.out.print("Enter rating x.y/10: ");
        double rating = reader.nextDouble();
        reader.nextLine(); //Consume \n character

        System.out.print("Enter Genre: ");
        String genre = reader.nextLine();

        System.out.print("Enter WatchList status (true or false): ");
        Boolean watchList = reader.nextBoolean();

        return new Movie(title, year, director, actors, rating, genre, watchList);

    }


    /**
     * Creates a movie from raw information passed into it
     * @param: Each of the values required for a movie. All objects must be present. 
     * @returns N/A
     *
     */
    public Movie(String title, Integer year, String director, ArrayList<String> actors, Double rating, String genre, Boolean watchList)
    {
        m_title = title;
        m_year = year;
        m_director = director;
        m_actors = new ArrayList<String>();
        m_rating = rating;
        m_genre = genre;
        m_onWatchList = watchList;

        for (String actor: actors)
        {
            m_actors.add(actor);
        }
    }
    
    /**
     * Returns a string representation of the movie with all applicable information
     * @param None
     * @return A String representing the movie
     */
    public String toString()
    {
        String actorstr = "\n";

        for (String actor: m_actors)
        {
            actorstr += String.format("%s\n", actor);
        }

        return String.format("Title: %s\nYear: %d\nDirector: %s\n\nActors:%s\nRating: %.1f/10.0\nGenre: %s\nWatchList: %b\n", m_title, m_year, m_director, actorstr, m_rating, m_genre, m_onWatchList);
    }

    //Please don't shoot me for this
    //https://stackoverflow.com/a/53226346/5763413
    //Stack overflow said to do it
    @SuppressWarnings("unchecked")
    /**
     * Creates a JSONObject of the movie
     * @param None
     * @return a JSONObject
     *
     */
    public  JSONObject toJSON()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("title",m_title) ;
            obj.put("director",m_director) ;
            obj.put("year", Integer.valueOf(m_year));
            obj.put("rating", m_rating);
            obj.put("genre", m_genre);
            obj.put("onWatchList", m_onWatchList);

            JSONArray actors = new JSONArray();

            for (String actor: m_actors)
            {
                actors.add(actor);
            }
            obj.put("ActorList", actors);

        }

        catch (Exception e) {e.printStackTrace();}

        return obj;
    }

    /**
     * Returns the title of the movie
     * @param None
     * @return String of title
     */
    public String getTitle()
    {
        return m_title;
    }

    /**
     * Returns the release year of the movie
     * @param None
     * @return Integer of release year
     */
    public Integer getYear()
    {
        return m_year;
    }

    /**
     * Returns the director of the movie
     * @param None
     * @return String of director
     */
    public String getDirector()
    {
        return m_director;
    }

    /**
     * Returns the actors of the movie
     * @param None
     * @return ArrayList<String> of actors
     */
    public ArrayList<String> getActors()
    {
        return m_actors;
    }
    
    /**
     * Returns the rating of the movie
     * @param None
     * @return A double with the rating of the movie
     */
    public Double getRating()
    {
        return m_rating;
    }

    /**
     * Returns the genre of the movie
     * @param None
     * @return String of genre
     */
    public String getGenre()
    {
        return m_genre;
    }

    /**
     * Gets the watchlist status of the movie
     * @param none
     * @return Boolean 
     */
    public Boolean getWatchList() { return m_onWatchList; }
    
    /**
     * Sets the watchlist status of the movie
     * @param Boolean new value to be set 
     * @return None
     */
    public void setWatchList(Boolean newValue) { m_onWatchList = newValue; }

}
