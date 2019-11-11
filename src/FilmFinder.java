import java.util.ArrayList;

/**
 * The FilmFinder class. It is a Singleton which means only one
 * can exist at a time.
 *
 * This class serves as a middleman between the SearchBuilder and the rest of
 * the classes within the model. When the program initializes, it retrieves Movie information
 * from the Database via the Scraper class. While the program is running, it receives
 * instructions from the SearchBuilder to accurately build a ResultsList; whose information
 * then gets passed to the ResultsView object to be displayed in the GUI. This class also
 * interacts with the UserProfile object (which is used to store the WatchList) and collects
 * the updated WatchList to make the appropriate changes to the Database via
 * DatabaseUpdater when the program is terminated.
 *
 * @author bradley bowen
 *
 */

public class FilmFinder {
 //Attributes
    private FilmFinder          m_FilmFinder; //reference to itself
    private ArrayList<Movie>    m_MasterList;
    private UserProfile         m_UserProfile;
    private Scraper             m_Scraper;
    private ArrayList<Movie>    m_ResultsList;

 //Methods
    /**
     * A constructor for an empty FilmFinder object.
     */
    public FilmFinder ()
    {
        m_FilmFinder = this; //gives FilmFinder a reference to itself.
       m_MasterList = new ArrayList<Movie>();
       m_UserProfile = new UserProfile();
       m_ResultsList = new ArrayList<Movie>(); //storage.

       //This assumes Scraper is created in main program.

    }

   //Methods
   /**
    * Overloaded Constructor. Assumes Scraper is passing list of movie objects and UserProfile data (if any) to itself at start
    * of program.
    */
   public FilmFinder (ArrayList<Movie> masterList, String userName, ArrayList<Movie> watchList,
                       Scraper scraper)
   {
      m_FilmFinder = this; //gives FilmFinder a reference to itself.
      m_MasterList = masterList;
      m_UserProfile = new UserProfile(userName, watchList); //if no profile userName passed in
                                                            // should be "" and watchList empty.
      m_Scraper = scraper;
      m_ResultsList = new ArrayList<Movie>(); //Create storage for storing results generated after SearchBuilder
                                             //interaction.

   }

    /**
     * A constructor for an empty student object.
     */
    public FilmFinder getInstance()
    {
        return this; //returns a reference to this FilmFinder object
    }

   /**
    * Receives data from SearchBuilder class, and then creates the set of movies found.
    * Currently, the format of the data received from SearchBuilder is unclear to me.
    * Finally, it passes this list in String format to ResultsList to be displayed.
    */
   public ArrayList<String> createResultsList(ArrayList<String> searchData)
   {
      m_ResultsList.clear(); //clears all previous search results

      //PseudoCode
      //while(Not Done)
      //{
      //   m_ResultsList.add(m_MasterList.get())
      //}

      //Convert Movie objects into Strings
      //toString() FORMAT: String.format("Title: %s\nYear: %d\nDirector: %s\nActors:%s\nRating: %.1f/5, Genre: %s",
      //                                  m_title, m_year, m_director, actors, m_rating, m_genre);
      ArrayList<String> stringResults = new ArrayList<String>();
      //PseudoCode
      //while(Not Done)
      //{
      //    Movie temp;
      // temp = m_ResultsList.get()
      //   stringResults.add(temp.toString())
      //}

      return stringResults; //Assuming it will be used something like this:
      //                         SearchBuilder calls ResultsView.setResults = FilmFinder.createResultsList(searchData);
      //

   }
}
