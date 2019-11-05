import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

import java.io.*;

import java.util.ArrayList;



public class DBWizard{
    

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
         
        ArrayList<Movie> MovieList = new ArrayList<>();

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

        createSerial(MovieList);

        return;
    }
}
