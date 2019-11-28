import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.awt.Image;
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
    /**
     * static ResultsView instance- keeps track of ResultsView instance for Singleton implementation
     * JList m_results- used to display list of Movies found during the search
     * JTextArea m_movieInfo- displays all information about a Movie object (title, actors, director, etc.)
     * JLabel m_posterSpace- used to display the poster for the selected Movie
     * String m_posterLocation- contains the filepath to the poster for the selected Movie
     * Image m_posterScale- used to hold a copy of the poster that has been scaled to the size of m_posterSpace
     */
    private static ResultsView instance = null;
    private JList m_results;
    private JTextArea m_movieInfo;
    private JLabel m_posterSpace;
    private String m_posterLocation;
    private Image m_posterScale;

    /**
     * Constructor function used to initialize the ResultsView JPanel
     */
    private ResultsView()
    {
        setLayout(null);
        setBackground(Color.GRAY);
        setBounds(277,5, 505, 453);
        setBorder(BorderFactory.createLineBorder(Color.black, 3));

        m_results = new JList();
        m_results.setBounds(20, 20, 220, 410);
        m_results.setBackground(Color.LIGHT_GRAY);
        add(m_results);

        m_movieInfo = new JTextArea();
        m_movieInfo.setBounds(263, 220, 220, 210);
        m_movieInfo.setBackground(Color.LIGHT_GRAY);
        m_movieInfo.setEditable(false);
        add(m_movieInfo);

        m_posterSpace = new JLabel();
        m_posterSpace.setBounds(263, 20, 150, 190);
        add(m_posterSpace);
    }

    /**
     * This function returns the instance of ResultsView. If no instance exists, then one is created.
     */
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
     *  It then uses m_results to display the titles of these Movies.
     *  Whenever a new item from m_results is selected, m_movieInfo and m_posterSpace are updated with the information and poster for that Movie
     *
     * @param passMovies: ArrayList containing Movie objects whose info needs to be displayed
     *
     */
    public void showMoviesText(ArrayList<Movie> passMovies) {

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

        m_results = new JList(m_titles); //create new list using titles from passMovies
        m_results.setBounds(20, 20, 220, 410);
        m_results.setBackground(Color.LIGHT_GRAY);
        m_results.setSelectedIndex(0); //set default index for JList
        add(m_results); //add the JList to the JPanel


        m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //get initial information for JTextArea to display
        add(m_movieInfo);


        m_posterLocation = new String("images/");
        m_posterLocation += m_titles[m_results.getSelectedIndex()].replaceAll(" ", "_"); //create the filepath to find the poster in the images folder
        m_posterLocation += ".jpg";

        ImageIcon poster = new ImageIcon(m_posterLocation);
        m_posterScale = poster.getImage();
        m_posterScale = m_posterScale.getScaledInstance(150, 190, Image.SCALE_SMOOTH); //create a version of the poster scaled to the size of the JLabel

        m_posterSpace.setIcon(new ImageIcon(m_posterScale)); //set the scaled poster in the JLabel

        m_results.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent arg0) { //whenever a new Movie in the JList is selected...
                if (!arg0.getValueIsAdjusting()) {
                    m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //..change the data displayed within the JTextArea...

                    m_posterLocation = "images/";
                    m_posterLocation += m_titles[m_results.getSelectedIndex()].replaceAll(" ", "_"); //...find the filepath for the new selection's poster...
                    m_posterLocation += ".jpg";

                    ImageIcon poster = new ImageIcon(m_posterLocation);
                    m_posterScale = poster.getImage();
                    m_posterScale = m_posterScale.getScaledInstance(150, 190, Image.SCALE_SMOOTH); //...scale the new poster...

                    m_posterSpace.setIcon(new ImageIcon(m_posterScale)); //..and display it
                }
            }
        });
    }

}