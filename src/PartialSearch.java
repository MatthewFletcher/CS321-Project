import java.util.ArrayList;

public class PartialSearch{
 

    private static PartialSearch instance = null;


    /**
     * Private constructor for Singleton
     *
     */
    private PartialSearch()
    {

    }
    
    /**
     * Singleton method to create one instance of PartialSearch using constructor and return it to the caller. If one instance
     * already exists it returns a reference to that instance instead. Allows only one instance of PartialSearch to exist.
     * @return returns a reference to the one and only instance of PartialSearch.
     */
    public static PartialSearch getInstance()
    {
        if (instance == null)
        {
            instance = new PartialSearch();
        }

        return instance;
    }
    /**
     * Dummy standin until i get the actual compare working
     */
    private Boolean hasSubstringMatch(String str1, String str2)
    {
        return str1.toLowerCase().contains(str2.toLowerCase());
    }

    /**
     * Compare titles
     */
    private Boolean matchTitle(Movie m1, Movie m2)
    {
        return (hasSubstringMatch(m1.getTitle(), m2.getTitle()));
    }


    private Boolean matchDirector(Movie m1, Movie m2)
    {
        return (hasSubstringMatch(m1.getDirector(), m2.getDirector()));
    }

    public ArrayList<Movie> Search(Movie searchMovie)
    {
        DBWizard d = new DBWizard();
        ArrayList<Movie> MasterList = new ArrayList<Movie>();
        MasterList = d.readDB();

        ArrayList<Movie> matchList = new ArrayList<Movie>();

        for (Movie m : MasterList)
        {
            if (searchMovie.getTitle() != null)
            {
                if (matchTitle(m, searchMovie)) matchList.add(m);
                continue;
            }

            if (searchMovie.getYear() != null)
            {
                if (matchYear(m, searchMovie)) matchList.add(m);
                continue;
            }

            if (searchMovie.getDirector() != null)
            {
                if (matchDirector(m, searchMovie)) matchList.add(m);
                continue;
            }
            
        }

        return matchList; 

    }



}
