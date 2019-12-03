import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     * JScrollPane m_resultsPane- used to add a scrollbar to m_results
     * JTextArea m_movieInfo- displays all information about a Movie object (title, actors, director, etc.)
     * JScrollPane m_infoPane- used to add a scrollbar to m_movieInfo
     * JLabel m_posterSpace- used to display the poster for the selected Movie
     * JLabel m_badPoster- used to display special image when no results are found
     * Image m_posterScale- used to hold a copy of the poster that has been scaled to the size of m_posterSpace
     * JButton m_showWatchList- fetches WatchList from UserProfile via FilmFinder and displays the list
     * JButton m_toggleWatchList- toggles a Movie's WatchList status
     * String m_titles[]- array of Strings used in m_results
     * String m_description[]- array of Strings used in m_movieInfo
     */
    private static ResultsView instance = null;
    private JList m_results;
    private JScrollPane m_resultsPane;
    private JTextArea m_movieInfo;
    private JScrollPane m_infoPane;
    private JLabel m_posterSpace;
    private JLabel m_badPoster;
    private Image m_posterScale;
    private JButton m_showWatchList;
    private JButton m_toggleWatchList;
    private String m_titles[];
    private String m_descriptions[];

    /**
     * Constructor function used to initialize the ResultsView JPanel
     */
    private ResultsView()
    {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(277,5, 505, 453);
        setBorder(BorderFactory.createLineBorder(Color.black, 3));

        m_results = new JList();
        m_results.setBackground(Color.WHITE);
        m_resultsPane = new JScrollPane();
        m_resultsPane.setBounds(20, 20, 220, 380);
        m_resultsPane.setViewportView(m_results); //put JList in JScrollPane before displaying
        add(m_resultsPane);

        m_movieInfo = new JTextArea();
        m_movieInfo.setBackground(Color.WHITE);
        m_movieInfo.setEditable(false);
        m_infoPane = new JScrollPane(m_movieInfo); //put JTextArea in JScrollPane before displaying
        m_infoPane.setBounds(263, 220, 220, 170);
        add(m_infoPane);

        m_posterSpace = new JLabel();
        m_posterSpace.setBounds(298, 20, 150, 190);
        add(m_posterSpace);

        m_badPoster = new JLabel();
        add(m_badPoster);

        m_showWatchList = new JButton("Display WatchList");
        m_showWatchList.setBounds(20, 410, 220, 20);
        add(m_showWatchList);

        m_showWatchList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                //change WatchList flag within Movie object
                FilmFinder.getInstance().passWatchList(); //display WatchList whenever the button is pressed
            }
        });

        m_toggleWatchList = new JButton("Toggle WatchList Status");
        m_toggleWatchList.setBounds(263, 400, 220, 30);
        add(m_toggleWatchList);
    }

    /**
     * This function returns the instance of ResultsView. If no instance exists, then one is created.
     *
     * @return instance- pointer to instance of ResultsView to be used
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
        m_titles = new String[passMovies.size()]; //create String array of movie titles to put in the JList
        m_descriptions = new String[passMovies.size()]; //create String array to hold movie toString data

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

        m_results.clearSelection();
        m_results.setListData(m_titles);
        m_results.setSelectedIndex(0); //set default index for JList

        if(m_titles[0].contains("No movies found")) //if there are no results found, display the special output for that scenario
        {
            m_movieInfo.setText("No results found.\n\nPlease try again.");
            ImageIcon poster = new ImageIcon("images/None.jpg");
            m_posterScale = poster.getImage();
            m_posterScale = m_posterScale.getScaledInstance(220, 160, Image.SCALE_SMOOTH);

            m_posterSpace.setText("");
            m_badPoster.setBounds(263, 30, 220, 160);
            m_badPoster.setIcon(new ImageIcon(m_posterScale)); //set the scaled poster in the JLabel
        }
        else { //otherwise, display Movie list and information
            m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //get initial information for JTextArea to display
            m_movieInfo.setCaretPosition(0);


            String m_posterLocation = new String("images/");
            m_posterLocation += m_titles[m_results.getSelectedIndex()].replaceAll("[ |:$|?$]", "_"); //create the filepath to find the poster in the images folder
            m_posterLocation += ".jpg";

            ImageIcon poster = new ImageIcon(m_posterLocation);
            m_posterScale = poster.getImage();
            m_posterScale = m_posterScale.getScaledInstance(150, 190, Image.SCALE_SMOOTH); //create a version of the poster scaled to the size of the JLabel

            m_badPoster.setBounds(298, 20, 150, 190);
            m_badPoster.setIcon(new ImageIcon(m_posterScale));
            m_posterSpace.setIcon(new ImageIcon(m_posterScale)); //set the scaled poster in the JLabel
        }

        m_results.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent arg0) { //whenever a new Movie in the JList is selected...
                if (!arg0.getValueIsAdjusting()) {
                    if (m_results.getSelectedIndex() < 0) return;

                    m_movieInfo.setText(m_descriptions[m_results.getSelectedIndex()]); //..change the data displayed within the JTextArea...
                    m_movieInfo.setCaretPosition(0);

                    String m_posterLocation = "images/";
                    m_posterLocation += m_titles[m_results.getSelectedIndex()].replaceAll("[ |:$|?$]", "_"); //...find the filepath for the new selection's poster...
                    m_posterLocation += ".jpg";

                    ImageIcon poster = new ImageIcon(m_posterLocation);
                    m_posterScale = poster.getImage();
                    m_posterScale = m_posterScale.getScaledInstance(150, 190, Image.SCALE_SMOOTH); //...scale the new poster...

                    m_posterSpace.setIcon(new ImageIcon(m_posterScale)); //..and display it
                }
            }
        });

        m_toggleWatchList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                //change WatchList flag within Movie object
                FilmFinder.getInstance().changeWatchList(passMovies.get(m_results.getSelectedIndex())); //change a Movie's onWatchList value and add it to/remove it from the WatchList
            }
        });
    }

}