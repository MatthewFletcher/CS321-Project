import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DBWizard{

    //Set file name 
    public static final String DBNAME = "../data/mydb.json";

    //Don't shoot me for this.
    //https://stackoverflow.com/a/53226346/5763413
    //Stack overflow said to do it
    @SuppressWarnings("unchecked")



    public static void writeDB(ArrayList<Movie> ml)
    {
        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();

        for (Movie m:ml)
        {
            list.add(m.toJSON());
        }

        obj.put("MovieList", list);

        try(FileWriter file =  new FileWriter(DBNAME))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public static ArrayList<Movie> readDB()
    {
        JSONParser parser = new JSONParser();

        ArrayList<Movie> theList = new ArrayList<Movie>();

        try {
            Object obj = parser.parse(new FileReader(DBNAME));
            //
            //Read JSON file
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray movieList = (JSONArray) jsonObject.get("MovieList");

            for (Object j : movieList)
            {
                theList.add(new Movie((JSONObject)j));
            }
    

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return theList;
    }

    public void addMovie(Movie m)
    {
        
    }




    public static void main(String args[])
    {

        System.out.println("##########\nReading DB");
        ArrayList<Movie> MovieList = readDB();
        
        //Get user input
        Scanner kb = new Scanner(System.in);

        System.out.println("\nPRINTING MOVIE LIST");
        for (Movie m: MovieList)
        {
            System.out.println(m);
            System.out.println(); 
        }
        System.out.println("END MOVIE LIST");

        System.out.println("Generating new movie");
        Movie temp = Movie.createMovie();
        //Movie temp1 = Movie.createMovie();
        //Movie temp2 = Movie.createMovie();
        //Movie temp3 = Movie.createMovie();
        //Movie temp4 = Movie.createMovie();
        System.out.println("Generated movie:");
        System.out.println(temp);
        //System.out.println(temp1);
        //System.out.println(temp2);
        //System.out.println(temp3);
        //System.out.println(temp4);

        MovieList.add(temp);
        //MovieList.add(temp1);
        //MovieList.add(temp2);
        //MovieList.add(temp3);
        //MovieList.add(temp4);



        writeDB(MovieList);



        return;
}
}
