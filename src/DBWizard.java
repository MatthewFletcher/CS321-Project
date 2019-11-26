import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DBWizard{

    public static final String DBNAME = "mydb.json";

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
            System.out.println("jsonObject: " + jsonObject);
            JSONArray movieList = (JSONArray) jsonObject.get("MovieList");

            for (Object j : movieList)
            {
                System.out.printf("Movie: %s\n", (JSONObject)j);
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




    public static void main(String args[])
    {
        System.out.println("##########Reading DB");
        ArrayList<Movie> MovieList = readDB();

        System.out.println("#########Writing Movie DB");
        writeDB(MovieList);



        return;
    }
}
