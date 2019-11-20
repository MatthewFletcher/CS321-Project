import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class name: ResultsView
 *
 * This class is used to hold and display the movie information to the user.
 * The class extends JPanel so it can be a component within the MainView, alongside the SearchView.
 *
 */

public class ResultsView extends JPanel
{
    private static ResultsView instance = null;
    private JList m_results;   //JList used to display movie titles
    private JTextArea m_movieInfo; //JTextArea used to display complete movie info

    /**
     * Constructor function used to initialize the ResultsView JPanel
     */
    private ResultsView()
    {
        setLayout(null);
        setBackground(Color.GRAY);
        setBounds(277,5, 505, 453);
        setBorder(BorderFactory.createLineBorder(Color.black, 3));

        String initialize[] = new String[1];
        m_results = new JList(initialize);
        m_results.setBounds(20, 20, 220, 410);
        m_results.setBackground(Color.LIGHT_GRAY);

        m_movieInfo = new JTextArea();
        m_movieInfo.setBounds(263, 220, 220, 210);
        m_movieInfo.setBackground(Color.LIGHT_GRAY);
        m_movieInfo.setEditable(false);
    }

    public static ResultsView getInstance()
    {
        if (instance == null)
        {
            instance = new ResultsView();
        }

        return instance;
    }
    /**
     *  This function takes the ArrayList of Movies that it is given and creates an array of Movie.getTitle() and Movie.toString() results.
     *  It then creates a JList and JTextArea that are used to display this information and update whenever the user selects a new item from the JList
     *
     * @param passMovies: ArrayList containing Movie objects whose info needs to be displayed
     *
     */
    public void showMoviesText(ArrayList<Movie> passMovies)
    {

        int i = 0; //used to iterate through arrays when adding new objects
        String m_titles[] = new String[passMovies.size()]; //create String array of movie titles to put in the JList
        String m_descriptions[] = new String[passMovies.size()]; //create String array to hold movie toString data

        for (Movie pass : passMovies) {
            m_titles[i] = pass.getTitle(); //copy the title of every Movie in the ArrayList to the array of titles
            i++;
        }

        i = 0;
        for (Movie pass : passMovies)
        {
            m_descriptions[i] = pass.toString(); //copy the toString() results o  every Movie in the ArrayList to the array of descriptions
            i++;
        }

        m_results = new JList(m_titles);
        m_results.setBounds(20, 20, 220, 410);
        m_results.setBackground(Color.LIGHT_GRAY);
        m_results.setSelectedIndex(0); //set default index for JList
        add(m_results); //add the JList to the JPanel


        m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //get initial information for JTextArea to display
        add(m_movieInfo);

        m_results.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //whenever a new item in the JList is selected, change the data displayed within the JTextArea
                }
            }
        });

    }

}