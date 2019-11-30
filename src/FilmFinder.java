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
 */

public class FilmFinder {
 //Attributes
    private static FilmFinder   instance = null; //reference to itself
    private ArrayList<Movie>    m_MasterList;   //List of every movie from data file
    private UserProfile         m_UserProfile;  //reference to UserProfile
    private Scraper             m_Scraper;      //reference to Scraper
    private ArrayList<Movie>    m_ResultsList;  //List of most recent movie search

 //Methods

    /**
     * Private Constructor. Call by getInstance(). FilmFinder's remaining field values will be assigned
     * by other functions called outside of FilmFinder.
     */
    private FilmFinder ()
    {


        m_MasterList = new ArrayList<Movie>(); //Creates storage for movies
        m_UserProfile = new UserProfile();
        m_Scraper = null;
        m_ResultsList = new ArrayList<Movie>(); //Create storage for storing results generated after SearchBuilder interaction.

    }

    /**
     * STUB. Calls data scraper to create a new movie, and return to caller. We need to decide on a function for Scraper.
     * @return returns a newly create movie object.
     */
    public Movie createMovie()
    {
        //INSERT call to Scraper to get movie data

        return new Movie();
    }

    /**
     * STUB. Build the master list of movies at the beginning of program using createMovie()
     */
    public void createMasterList()
    {
         //INSERT loop to create and add all data file movies gathered by Scraper into the Master list

    }


    /**
     * Singleton method to create one instance of FilmFinder using constructor and return it to the caller. If one instance
     * already exists it returns a reference to that instance instead. Allows only one instance of FilmFinder to exist.
     * @return returns a reference to the one and only instance of FilmFinder.
     */
    public static FilmFinder getInstance()
    {
        if (instance == null)
        {
            instance = new FilmFinder();
        }

        return instance;
    }

    /**
     * Gets MasterMovie list so SearchBuilder can search through it
     * @return returns a reference to the the list containing all movies
     */
    public ArrayList<Movie> getMasterList()
    {
        return m_MasterList;
    }

    /**
     * Gets ResultsList so SearchBuilder can add movies to it while searching
     * @return returns a reference to the the list containing recent search results
     */
    public ArrayList<Movie> getResultsList()
    {
        return m_ResultsList;
    }


    /**
     * Receives list of movies found by the SearchBuilder class, updates m_resultsList, and finally passes the list to ResultsView to be displayed.
     * Currently, the format of the data received from SearchBuilder is unclear to me.
     */
    public void passResultsList()
    {
        ArrayList<Movie> passMovies = new ArrayList<Movie>();

        for (Movie m : m_ResultsList) { // iterate through the ResultsList and then passes to ResultsView
            passMovies.add(m);
        }

        //Get the instance of ResultsView to pass it the results.
        ResultsView resultsView = ResultsView.getInstance();

        //Passes the movie list result from the search to ResultsView
        resultsView.showMoviesText(passMovies);

    }

    /**
     * Allows the m_Scraper attribute to be assigned. This function is needed since the constructor is private.
     */
    public void setScraperReference(Scraper scraper)
    {
        m_Scraper = scraper;
    }

    /**
     * Loads test data into MasterList so We can test search function in searchBuilder
     */
    public void setMasterList(ArrayList<Movie> testList)
    {
        for (Movie m : testList) { // Set MasterList
            m_MasterList.add(m);
        }
    }

    public void createWatchList()
    {
        for (Movie m : m_MasterList)
        {
            if (m.getWatchList())
            {
                m_UserProfile.addMovie(m);
            }
        }
    }

    public void passWatchList() { ResultsView.getInstance().showMoviesText(m_UserProfile.getWatchList()); }

}



