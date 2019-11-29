import java.util.ArrayList;

public class SearchBuilder {

    //ATTRIBUTES
    private static SearchBuilder   instance = null; //reference to itself



    //METHODS
    /**
     * Private Constructor. Call by getInstance().
     */
    private SearchBuilder()
    {

    }

    /**
     * Singleton method to create one instance of SearchBuilder using constructor and return it to the caller. If one instance
     * already exists it returns a reference to that instance instead. Allows only one instance of SearchBuilder to exist.
     * @return returns a reference to the one and only instance of SearchBuilder.
     */
    public static SearchBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new SearchBuilder();
        }

        return instance;
    }

    /**
     * Searches through FilmFinder's MasterList of Movies according to the search constraints contained inside a single
     * Movie object's attributes. It sets the ResultsList inside of FilmFinder to whatever search finds, and
     * finally calls FilmFinder.passResultsList() to send results to ResultsView. Assumes all fields in constraints are
     * initialized to something, not NULL.
     *@param constraints: a new Movie object that holds the GUI SearchView results of a search
     */
    public void search(Movie constraints)
    {
        FilmFinder filmFinder = FilmFinder.getInstance(); //used to access Master Movie list
        filmFinder.getResultsList().clear(); //clears all previous search results



        //Checks if constraint attribute has any valid values in it
        Boolean titleNotEmpty = false;
        Boolean actorsNotEmpty = false;
        Boolean yearNotEmpty = false;
        Boolean directorNotEmpty = false;
        Boolean ratingNotEmpty = false;
        Boolean genreNotEmpty = false;

        //Switch that will indicate later which constraint attribute was added to tempList first
        Boolean titleFirst = false;
        Boolean actorsFirst = false;
        Boolean yearFirst = false;
        Boolean directorFirst = false;
        Boolean ratingFirst = false;
        Boolean genreFirst = false;


        Boolean firstSelected = false; //flag that says when the first valid/nonempty attribute has been selected.
        Boolean nothingFound = false; //flag that is triggered to save search time if nothing matches early.
        //Boolean somethingFound = false;

        //STAGE 1: setting flags----------------------------------------------------------
        if(constraints.getTitle().isEmpty() == false)
        {
            titleNotEmpty = true;
            if(firstSelected == false)
            {
                titleFirst = true;
                firstSelected = true;
            }
        }

        if(constraints.getActors().isEmpty() == false)
        {
            actorsNotEmpty = true;
            if(firstSelected == false)
            {
                actorsFirst = true;
                firstSelected = true;
            }
        }

        if(constraints.getYear().intValue() > -1)
        {
            yearNotEmpty = true;
            if(firstSelected == false)
            {
                yearFirst = true;
                firstSelected = true;
            }
        }

        if(constraints.getDirector().isEmpty() == false)
        {
            directorNotEmpty = true;
            if(firstSelected == false)
            {
                directorFirst = true;
                firstSelected = true;
            }
        }

        if(constraints.getRating().doubleValue() >= 0.0)
        {
            ratingNotEmpty = true;
            if(firstSelected == false)
            {
                ratingFirst = true;
                firstSelected = true;
            }
        }

        if(constraints.getGenre().isEmpty() == false)
        {
            genreNotEmpty = true;
            if(firstSelected == false)
            {
                genreFirst = true;
                firstSelected = true;
            }
        }

        //STAGE2 searching FilmFinder.MasterList and Adding to Temp List---------------------------------------------------------------

        ArrayList<Movie> tempList = new ArrayList<Movie>();


        //check for all fields empty
        if(firstSelected == false)
        {
            //then don't check anything else
            nothingFound = true;
        }
        //Find matches for first nonEmpty field
        else if(titleNotEmpty && titleFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                if( m.getTitle().toLowerCase().equals(constraints.getTitle().toLowerCase())) //compares Title name from Master to name VALUE in constraints
                    tempList.add(m); //add movie to results found


            }

        }

        //Find matches for first nonEmpty field
        else if(actorsNotEmpty && actorsFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                for(String a : m.getActors()) //iterates through every actor String inside movie m
                {
                    if( a.toLowerCase().equals(constraints.getActors().get(0).toLowerCase())) //compares actor name from Master to name VALUE in constraints.Actors[0]
                        tempList.add(m); //add movie to results found
                }
            }

        }

        //Find matches for first nonEmpty field
        else if(yearNotEmpty && yearFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                if( m.getYear().intValue() == constraints.getYear().intValue() ) //compares Year from Master to Year in constraints
                    tempList.add(m); //add movie to results found
            }

        }

        //Find matches for first nonEmpty field
        else if(directorNotEmpty && directorFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                if( m.getDirector().toLowerCase().equals(constraints.getDirector().toLowerCase())) //compares Director from Master to Director VALUE in constraints
                    tempList.add(m); //add movie to results found
            }

        }

        //Find matches for first nonEmpty field
        else if(ratingNotEmpty && ratingFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                if( m.getRating().intValue() == constraints.getRating().intValue() ) //compares Rating from Master to Rating in constraints
                    tempList.add(m); //add movie to results found
            }

        }

        //Find matches for first nonEmpty field
        else if(genreNotEmpty && genreFirst)
        {
            //search master list
            for(Movie m : filmFinder.getMasterList()) //iterates over every movie in Master list
            {
                if( m.getGenre().toLowerCase().equals(constraints.getGenre().toLowerCase())) //compares Genre from Master to Genre VALUE in constraints
                    tempList.add(m); //add movie to results found
            }

        }

        else
            nothingFound = true; //nothing matched the MasterList movies

        //Now, operate on TempList. REMOVING anything that doesn't match constraints -----------------------------------
        //tempListCOPY allows me to iterate over the elements and remove them at the same time. Otherwise an exception is thrown.
        ArrayList<Movie> tempListCOPY = new ArrayList<Movie>();

        //Copies all movies from tempList to COPY list
        for(Movie m : tempList)
        {
            tempListCOPY.add(m);
        }

        if(nothingFound) //skip searches if empty
        {

        }
        else //search remaining fields
        {
            if((titleFirst != true) && titleNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY) //iterates over every movie in tempListCOPY
                {
                    if((m.getTitle().equals(constraints.getTitle()) == false))
                    {
                        tempList.remove(m); //remove from original tempList
                    }
                }
                //Clear movies from COPY list, and then copy values from tempList into COPY
                tempListCOPY.clear();
                for(Movie m : tempList)
                {
                    tempListCOPY.add(m);
                }


            }

            if((actorsFirst != true) && actorsNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY)
                {
                    Boolean match = false;
                    for(String a : m.getActors())
                    {
                        if( (a.equals(constraints.getActors().get(0)) ) )
                        {
                            match = true;
                        }
                    }
                    if(match == false) //if every actor in movie m has been compared to search but nothing found, then remove it
                        tempList.remove(m);
                }

                tempListCOPY.clear();
                for(Movie m : tempList)
                {
                    tempListCOPY.add(m);
                }

            }

            if((yearFirst != true) && yearNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY)
                {
                    if( ( m.getYear().intValue() == constraints.getYear().intValue() ) == false)
                        tempList.remove(m);
                }

                tempListCOPY.clear();
                for(Movie m : tempList)
                {
                    tempListCOPY.add(m);
                }

            }

            if((directorFirst != true) && directorNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY)
                {
                    if( (m.getDirector().equals(constraints.getDirector()) ) == false)
                        tempList.remove(m);
                }

                tempListCOPY.clear();
                for(Movie m : tempList)
                {
                    tempListCOPY.add(m);
                }

            }

            if((ratingFirst != true) && ratingNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY)
                {
                    if( (m.getRating().intValue() == constraints.getRating().intValue() ) == false )
                        tempList.remove(m);
                }

                tempListCOPY.clear();
                for(Movie m : tempList)
                {
                    tempListCOPY.add(m);
                }

            }

            if((genreFirst != true) && genreNotEmpty  )
            {
                //search tempList
                for(Movie m : tempListCOPY) //iterates over every movie in tempListCOPY
                {
                    if( (m.getGenre().equals(constraints.getGenre()) ) == false )
                        tempList.remove(m);
                }

            }
        }

        //Nothing was found
        if(nothingFound == true || tempList.isEmpty())
        {
            //Create a movie object just for carrying a message.
            ArrayList<String> emptyActorList = new ArrayList<String>();
            Movie message = new Movie("No movies found with that search. Try again!\n", -1, "", emptyActorList, -1.0, "");
            filmFinder.getResultsList().add(message);
            filmFinder.passResultsList(); //pass message
        }
//        else if((somethingFound == true) && filmFinder.getResultsList().isEmpty()) //something was found but later removed.
//        {
//            Movie message = new Movie();
//            message.setTitle("No movies found with that search. Try reducing one search field and try again!\n");
//            filmFinder.getResultsList().add(message);
//            filmFinder.passResultsList(); //pass found movies
//        }
        else //Some movies were found! Copies results into FilmFinder's ResultsList attribute.
        {
            for(Movie m : tempList)
            {
                filmFinder.getResultsList().add(m);
            }

            filmFinder.passResultsList(); //pass found movies to ResultsView
        }


        System.out.println("SearchBuilder: Search finished!");

    }


    //Note: function is in UML model and Sequence diagram. However, SearchBuilder is creating the search results list,
            //not FilmFinder. So, this function probably should be removed. It breaks Model/Control separation.
//    public assembleMovieList()
//    {
//
//    }


}
