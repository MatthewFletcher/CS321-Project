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
 * DatabaseUpdater when the program is terminated. (Quoted from Design Doc)
 *
 * Note: I will probably update the description above since its function has deviated a bit from the original design
 *      in that it is not actively building its own lists from SearchBuilder's instructions.
 *
 * @author bradley bowen
 *
 */

public class FilmFinder {
 //Attributes
    private static FilmFinder   instance = null; //reference to itself
    private ArrayList<Movie>    m_MasterList;
    private UserProfile         m_UserProfile;
    private Scraper             m_Scraper;
    private ArrayList<Movie>    m_ResultsList;

 //Methods
    /**
     * A constructor for an empty FilmFinder object. Probably will never be used.
     */
    private FilmFinder ()
    {

    }

    //Methods
    /**
     * Overloaded private Constructor. Assumes Scraper is passing list of movie objects and UserProfile data (if any) to itself at start
     * of program. It is called indirectly by calling getInstance(Params...)
     *
     * @param masterList: ArrayList containing all Movie objects stored in the XML/data file.
     * @param userName: String containing the name of the user of the software.
     * @param watchList: ArrayList containing all movies in the WatchList if one exists.
     * @param scraper: reference to the instance of the Scraper class.
     */
    private FilmFinder (ArrayList<Movie> masterList, String userName, ArrayList<Movie> watchList, Scraper scraper)
    {
        instance = this; //gives FilmFinder a reference to itself.
        m_MasterList = masterList;
        m_UserProfile = new UserProfile(userName, watchList);
        m_Scraper = scraper;
        m_ResultsList = new ArrayList<Movie>(); //Create storage for storing results generated after SearchBuilder
        //interaction.

    }


    /**
     * Singleton method to create one instance of FilmFinder using overloaded constructor and return it to the caller.
     * Assumes Scraper is passing list of movie objects and UserProfile data (if any) to itself at start
     * of program.
     *
     * @param masterList: ArrayList containing all Movie objects stored in the XML/data file.
     * @param userName: String containing the name of the user of the software.
     * @param watchList: ArrayList containing all movies in the WatchList if one exists.
     * @param scraper: reference to the instance of the Scraper class.
     */
    public static FilmFinder getInstance(ArrayList<Movie> masterList, String userName, ArrayList<Movie> watchList, Scraper scraper)
    {
        if (instance == null)
        {
            instance = new FilmFinder(masterList, userName, watchList, scraper);
        }

        return instance;
    }

    /**
     * Overloaded Singleton method to return a reference to FilmFinder. Use it only after using the other getInstance(params...).
     */
    public static FilmFinder getInstance()
    {
        return instance;
    }


   /**
    * Receives list of movies found by the SearchBuilder class, updates m_resultsList, and finally passes the list to ResultsView to be displayed.
    * Currently, the format of the data received from SearchBuilder is unclear to me.
    */
   public void createResultsList(ArrayList<Movie> searchResults)
   {
      m_ResultsList.clear(); //clears all previous search results

       for (Movie m : searchResults) { // iterate through the movies found by SearchBuilder's search, and add them to m_ResultsList
           m_ResultsList.add(m);
       }

        //Get the instance of ResultsView to pass it the results.
       ResultsView resultsView = ResultsView.getInstance();

       //Passes the movie list result from the search to ResultsView
       resultsView.showMoviesText(m_ResultsList);

   }
}

