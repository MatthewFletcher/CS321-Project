import java.util.ArrayList;

/**
 * The UserProfile class holds the program user's current WatchList
 * of favorite or currently interesting movies. It also records the user's name.
 * @author bradley bowen
 *
 */

public class UserProfile {
 //Attributes
    private String              m_name;
    private ArrayList<Movie>    m_watchList;

 //Methods
    /**
     * Default Constructor for an empty UserProfile object.
     */
    public UserProfile ()
    {
      m_name = "";
      m_watchList = new ArrayList<Movie>(); //Creates storage for movies
    }

   /**
    * Overloaded Constructor
    */
   public UserProfile (String name, ArrayList<Movie> watchList)
   {
      m_name = name;
      m_watchList = watchList;
   }

   /**
    * Returns user's name
    */
   public String getName()
   {
      return m_name;
   }

    /**
     * Returns reference to user's current watchList
     */
    public ArrayList<Movie> getWatchList()
    {
        return m_watchList;
    }

   /**
    * Sets user's name
    */
   public void setName(String name)
   {
      m_name = name;
   }

   /**
    * Adds movie to current watchList
    */
   public void addMovie(Movie newMovie)
   {
      //Make sure newMovie is not already within watchList
      if(!m_watchList.contains(newMovie))
         m_watchList.add(newMovie);
      else
         System.out.println("Error: The movie is already in the WatchList");
   }

   /**
    * Removes movie from current watchList
    */
   public void removeMovie(Movie movie)
   {
      //Make sure movie is within watchList
      if(m_watchList.contains(movie))
         m_watchList.remove(movie);
      else
         System.out.println("Error: The movie is not in the WatchList");
   }


}




