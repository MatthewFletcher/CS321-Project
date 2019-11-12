import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

/**
 * Class name: ResultsView
 *
 * This class is used to hold and display the movie information to the user.
 * The class extends JPanel so it can be a component within the MainView, alongside the SearchView.
 *
 * @author Will Thomason
 */

public class ResultsView extends JPanel implements ListSelectionListener {

    String m_titles[]; //contains title of movies passed to ResultsView
    static JList m_results;   //JList used to display movie titles
    static JTextArea m_movieInfo; //JTextArea used to display complete movie info

    /**
     * Constructor function used to initialize the ResultsView JPanel
     */
    public ResultsView()
    {
        setSize(500, 400);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setVisible(true);
    }

    /**
     *
     * @param movies: ArrayList containing movie titles passed in by FilmFinder
     *
     * TO BE COMPLETED: once the functionality is in FilmFinder, pass in a second ArrayList containing results of Movie.toString() for result movies
     *
     */
    public void showMoviesText(ArrayList<String> movies)
    {

        ResultsView info = new ResultsView(); //instance of ResultsView used for the ListSelectionListener

        String m_titles[] = new String[movies.size()]; //create String array of movie titles to put in the JList

        for (int i = 0; i < movies.size(); i++) {
            m_titles[i] = movies.get(i); //copy from the ArrayList to the array
        }

        m_results = new JList(m_titles); //create new JList with SelectionListener and add it to the JPanel
        m_results.setSelectedIndex(0);
        m_results.addListSelectionListener(info);
        add(m_results);

        m_movieInfo = new JTextArea(); //create new JTextArea to display the information for the selected movie
        m_movieInfo.setText((String) m_results.getSelectedValue());
        add(m_movieInfo);

    }

    /**
     *
     * @param event: used to listen for changes to which value within the m_titles JList has been selected
     *
     * TO BE COMPLETED: once FilmFinder can pass ArrayList of Movie.toString() results, have text set to appropriate index from that ArrayList
     */
    public void valueChanged(ListSelectionEvent event)
    {
        m_movieInfo.setText((String) m_results.getSelectedValue());
    }


}
