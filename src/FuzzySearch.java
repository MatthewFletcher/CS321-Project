import java.util.ArrayList;

public class FuzzySearch{
 
    private static FuzzySearch theInstance = new FuzzySearch();

    //Threshold for fuzzy match
    private static final double THRESHOLD = 0.80;
    private static final int YEARTHRESH = 2;

    private static FuzzySearch instance = null;


    /**
     * Private constructor for Singleton
     *
     */
    private FuzzySearch()
    {

    }
    
    /**
     * Singleton method to create one instance of FuzzySearch using constructor and return it to the caller. If one instance
     * already exists it returns a reference to that instance instead. Allows only one instance of FuzzySearch to exist.
     * @return returns a reference to the one and only instance of FuzzySearch.
     */
    public static FuzzySearch getInstance()
    {
        if (instance == null)
        {
            instance = new FuzzySearch();
        }

        return instance;
    }
    /**
     * Dummy standin until i get the actual compare working
     */
    private double dummyCompare(String s1, String s2)
    {
        return 0.85;
    }

    /**
     * Compare titles
     */
    private Boolean matchTitle(Movie m, String title)
    {
        return (dummyCompare(m.getTitle(), title) > THRESHOLD);
    }

    private Boolean matchYear(Movie m, Integer year)
    {
        return (Math.abs(m.getYear() - year) < YEARTHRESH);
    }

    private Boolean matchDirector(Movie m, String director)
    {
        return (dummyCompare(m.getDirector(), director) > THRESHOLD);
    }

    public ArrayList<Movie> Search(Movie searchMovie)
    {
        DBWizard d = new DBWizard();
        ArrayList<Movie> MasterList = new ArrayList<Movie>();
        MasterList = d.readDB();

        ArrayList<Movie> matchList = new ArrayList<Movie>();

        for (Movie m : MasterList)
        {
            if (m.getTitle() != null)
            {
                if (matchTitle(m, searchMovie.getTitle())) matchList.add(m);
            }

            if (m.getYear() != null)
            {
                if (matchYear(m, searchMovie.getYear())) matchList.add(m);
            }

            if (m.getDirector() != null)
            {
                if (matchDirector(m, searchMovie.getDirector())) matchList.add(m);
            }
            
        }

        return matchList; 

    }



}
