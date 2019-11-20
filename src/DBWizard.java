import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class DBWizard{
        
    public static void writeDB(ArrayList<Movie> ml)
    {
        JSONArray list = new JSONArray();
        JSONObject obj = new JSONObject();

        for (Movie m:ml)
        {
            list.add(m.toJSON());
            
        }
        obj.put("MovieList", list);

        try(FileWriter file =  new FileWriter("mydb.json"))
        {
            file.write(obj.toString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void createSerial(ArrayList<Movie> l)
    {
        System.out.printf("Creating Serialized version\n");
        try{
        FileOutputStream fileOut = new FileOutputStream("./database.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);

        for (Movie m: l)
        {
            out.writeObject(m);
        } 

        out.close();
        fileOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }



    public static void main(String args[])
    {
         
        ArrayList<Movie> MovieList = new ArrayList<Movie>();

        ArrayList<String> temp = new ArrayList<>();
        temp.add("Bob Marley");
        temp.add( "Alice Snickerdoodle");
        MovieList.add(new Movie("Movie1", 1999, "Bob Smith",temp, 4.5, "Action"));

        temp.add("Barack obama");
        MovieList.add(new Movie("Movie2", 2001, "Bob Smith",temp, 3.5, "Comedy"));

        for (Movie m: MovieList)
        {
            System.out.println(m);
            System.out.println();

        }

        writeDB(MovieList);
        

//        createSerial(MovieList);
            
        
        return;
    }
}
