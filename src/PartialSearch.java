import java.util.ArrayList;

public class PartialSearch extends SearchBuilder{


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
    */
    private Boolean hasSubstringMatch(String master, String slave)
    {
        return master.toLowerCase().contains(slave.toLowerCase());
    }

    /**
     * Compare titles
     */
    private Boolean matchTitle(Movie m1, Movie m2)
    {
        if (hasSubstringMatch(m1.getTitle(), m2.getTitle()))
        {
            System.out.println("Titles match");
            System.out.println(m1.getTitle());
            System.out.println(m2.getTitle());
            return true;
        }
        else
        {
            return false;
        }

    }


    private Boolean matchDirector(Movie m1, Movie m2)
    {
        return (hasSubstringMatch(m1.getDirector(), m2.getDirector()));
    }

    private Boolean matchGenre(Movie m1, Movie m2)
    {
        return (hasSubstringMatch(m1.getGenre(), m2.getGenre()));
    }

    private Boolean matchActor(Movie m1, Movie m2)
    {
        boolean conforms = false;

        for (String actor: m1.getActors()) {
            if (hasSubstringMatch(actor, m2.getActors().get(0))) conforms = true;
        }

        return conforms;
    }

    @Override
    public void search(Movie searchMovie)
    {
        FilmFinder filmFinder = FilmFinder.getInstance(); //used to access Master Movie list
        filmFinder.getResultsList().clear(); //clears all previous search results
        DBWizard d = new DBWizard();
        ArrayList<Movie> MasterList = new ArrayList<Movie>();
        MasterList = d.readDB();

        ArrayList<Movie> matchList = new ArrayList<Movie>();

        for (Movie m : MasterList)
        {
            Boolean add = false;
            if (searchMovie.getTitle() != null)
            {
                if (matchTitle(m, searchMovie)) 
                {
                    add = true;
                    System.out.printf("TITLE MATCHES\n");
                }
                else
                {
                    add = false;
                    continue;
                }

            }

            if (searchMovie.getDirector() != null)
            {
                if (matchDirector(m, searchMovie)) 
                {
                    add = true;
                    System.out.printf("DIRECTOR MATCHES\n");
                }
                else
                {
                    add = false;
                    continue;
                }

            }

            if (searchMovie.getGenre() != null)
            {
                if (matchGenre(m, searchMovie)) 
                {
                    add = true;
                    System.out.printf("GENRE MATCHES\n");
                }
                else
                {
                    add = false;
                    continue;
                }

            }

            if (!searchMovie.getActors().isEmpty())
            {
                if (matchActor(m, searchMovie))
                {
                    add = true;
                    System.out.printf("ACTOR MATCHES\n");
                }
                else
                {
                    continue;
                }
            }
            
            if (add) 
            {
                System.out.printf("Movie %s matched and added", m.getTitle());
                matchList.add(m);
            }
        }
        System.out.println("Printing all matched movies");

        for (Movie m: matchList)
        {
            System.out.println(m.toString());
        }

        //Nothing was found
        if(matchList.isEmpty())
        {
            //Create a movie object just for carrying a message.
            ArrayList<String> emptyActorList = new ArrayList<String>();
            Movie message = new Movie("No movies found with that search. Try again!\n", -1, "", emptyActorList, -1.0, "", false);
            filmFinder.getResultsList().add(message);
            filmFinder.passResultsList(); //pass message
        }
        else //Some movies were found! Copies results into FilmFinder's ResultsList attribute.
        {
            System.out.println("MOVIES FOUND");
            for(Movie m : matchList)
            {
                filmFinder.getResultsList().add(m);
            }
            filmFinder.passResultsList(); //pass found movies to ResultsView
        }
    }



}
