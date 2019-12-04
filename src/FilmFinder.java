import java.util.ArrayList;

/**
 * FilmFinder is a Singleton class (only once instance allowed). It holds the master list of all movies
 * after they have been retrieved from a local data file by the DBWizard. It also holds recent searches performed
 * by SearchBuilder. Finally, it can update UserProfile's watchList data.
 *
 */

public class FilmFinder {
 //Attributes
    private static FilmFinder   instance = null; //reference to itself
    private ArrayList<Movie>    m_MasterList;   //List of every movie from data file
    private UserProfile         m_UserProfile;  //reference to UserProfile
    private ArrayList<Movie>    m_ResultsList;  //List of most recent movie search

 //Methods

    /**
     * Private Constructor. Call by getInstance(). FilmFinder's remaining field values will be assigned
     * by other functions called outside of FilmFinder.
     */
    private FilmFinder ()
    {

        m_MasterList = DBWizard.readDB(); //Creates storage for movies
        m_UserProfile = new UserProfile();
        createWatchList();
        m_ResultsList = new ArrayList<Movie>(); //Create storage for storing results generated after SearchBuilder interaction.

    }


    /**
     * Singleton method to create one instance of FilmFinder using constructor and return it to the caller. If one instance
     * already exists it returns a reference to that instance instead. Allows only one instance of FilmFinder to exist.
     * @return returns a FilmFinder reference to the one and only instance of FilmFinder.
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
     * @return returns an ArrayList<Movie> reference to the the list containing all movies
     */
    public ArrayList<Movie> getMasterList()
    {
        return m_MasterList;
    }

    /**
     * Gets ResultsList so SearchBuilder can add movies to it while searching
     * @return returns an ArrayList<Movie> reference to the resultsList containing recent search results
     */
    public ArrayList<Movie> getResultsList()
    {
        return m_ResultsList;
    }


    /**
     * Passes a copy of the resultsList to ResultsView to be displayed after a search
     * has been performed by SearchBuilder.
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
     *Adds movies to UserProfile's WatchList as long as WatchList has been initialized.
     */
    public void createWatchList()
    {

        m_UserProfile.getWatchList().clear();

        for (Movie m : m_MasterList)
        {
            if (m.getWatchList())
            {
                m_UserProfile.addMovie(m);
            }
        }
    }

    /**
     *Passes UserProfile's watchList to ResultsView to be displayed onscreen.
     */
    public void passWatchList()
    {

        ArrayList<Movie> passMovies = new ArrayList<Movie>();

        for (Movie m : m_UserProfile.getWatchList()) { // iterate through the WatchList and then passes to ResultsView
            passMovies.add(m);
        }

        //Get the instance of ResultsView to pass it the results.
        ResultsView resultsView = ResultsView.getInstance();

        //Passes the movie list result from the search to ResultsView
        resultsView.showMoviesText(passMovies);
    }

    /**
     * Adds a Movie to the UserProfile's WatchList
     * @param title- name of the Movie to be added to the WatchList
     */
    public void addWatchList(String title)
    {
        Boolean check = false;
        for (Movie m : m_MasterList) {
            if (m.getTitle().equals(title)) {
                m.setWatchList(true);
                check = true;
            }
        }

        if(check) { createWatchList(); }
    }

    /**
     * Rebuilds the WatchList without the Movie with the same title as the passed in String
     *
     * @param title- name of the Movie to be removed from the WatchList
     */

    public void removeWatchList(String title)
    {
        Boolean check = false;
        for (Movie m : m_UserProfile.getWatchList()) {
            if (m.getTitle().equals(title)) {
                m.setWatchList(false);
                check = true;
            }
        }

        if(check) { createWatchList(); }
    }
}




