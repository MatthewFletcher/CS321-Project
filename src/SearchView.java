import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Extends the JPanel interface
 * Initializes the input fields for the searches and contains them inside of its JPanel
 * Has the Singleton design pattern
 */
public class SearchView extends JPanel {

    /**
     * static MainView instance- keeps track of MainView instance for Singleton implementation
     * JTextField enterTitle- takes user input for using the movie's title in a search
     * JComboBox enterRating- takes user input for using minimum rating in a search
     * JTextField enterDirector- takes user input for using the movie's director in a search
     * JComboBox enterYear- takes user input for using the movie's year in a search
     * JTextField enterGenre- takes user input for using the genre title in a search
     * JTextField enterActor- takes user input for using one of the movie's actors in a search
     * JCheckBox enableFuzzySearch- takes user input to enable/disable fuzzy searching
     */
    private static SearchView instance = null;
    private JTextField enterTitle;
    private JComboBox enterRating;
    private JTextField enterDirector;
    private JComboBox enterYear;
    private JTextField enterGenre;
    private JTextField enterActor;
    private JCheckBox enableFuzzySearch;

    /**
     * Default constructor
     * Initializes all the search constraint input fields as GUI assets
     * Contains all input fields in a JPanel
     * Has no layout manager so fields can be placed at exact coordinates
     */
    private SearchView()
    {
        //Setting SearchView JPanel Settings
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(5,100, 267, 358);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        //Searching by title
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 15, 80, 20);
        add(titleLabel);
        enterTitle = new JTextField("");
        enterTitle.setBounds(55, 15, 185, 20);
        add(enterTitle);
        JButton searchByTitle = new JButton("Search by Title");
        searchByTitle.setBounds(20, 45, 220, 20);
        searchByTitle.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                //Creating movie object from GUI input fields
                Movie go = new Movie(enterTitle.getText(), -1, "",
                        new ArrayList<String>(), -1d, "", false);

                //Debug
                System.out.println("STARTING SEARCH, SEARCH PARAMETERS LISTED BELOW:");
                System.out.println(go.toString()); //debug

                //Search
                if (enableFuzzySearch.isSelected()) PartialSearch.getInstance().Search(go); //Fuzzy Search
                else SearchBuilder.getInstance().search(go); //Regular Search
            }
        });
        add(searchByTitle);


        //Searching by description
        String[] ratingArray = new String[102];
        for (int i = 0; i < 101; i++) {
            ratingArray[i + 1] = Double.toString(((double) i) / 10);
        }
        ratingArray[0] = "None";
        JLabel ratingLabel = new JLabel("Minimum Rating:");
        ratingLabel.setBounds(20, 110, 180, 20);
        add(ratingLabel);
        enterRating = new JComboBox(ratingArray);
        enterRating.setBounds(123, 110, 117, 20);
        add(enterRating);
        JLabel directorLabel = new JLabel("Director:");
        directorLabel.setBounds(20, 140, 80, 20);
        add(directorLabel);
        enterDirector = new JTextField("");
        enterDirector.setBounds(75, 140, 165, 20);
        add(enterDirector);
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 170, 80, 20);
        add(yearLabel);
        String[] yearArray = new String[133];
        for (int i = 0; i < 132; i ++) {
            yearArray[i] = Integer.toString(i + 1888);
        }
        yearArray[132] = "Any";
        enterYear = new JComboBox(yearArray);
        enterYear.setSelectedIndex(132);
        enterYear.setBounds(55, 170, 185, 20);
        add(enterYear);
        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(20, 200, 80, 20);
        add(genreLabel);
        enterGenre = new JTextField("");
        enterGenre.setBounds(65, 200, 175, 20);
        add(enterGenre);
        JLabel actorLabel = new JLabel("Actor:");
        actorLabel.setBounds(20, 230, 80, 20);
        add(actorLabel);
        enterActor = new JTextField("");
        enterActor.setBounds(62, 230, 178, 20);
        add(enterActor);
        JButton searchByDescription = new JButton("Search by Description");
        searchByDescription.setBounds(20, 260, 220, 20);
        searchByDescription.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                //Creating movie object from GUI input fields
                int year = -1;
                double rating = -1;
                ArrayList<String> actors = new ArrayList<String>();
                if (!enterYear.getSelectedItem().equals("Any")) year = Integer.parseInt((String) enterYear.getSelectedItem());
                if (!enterRating.getSelectedItem().equals("None")) rating = Double.parseDouble((String) enterRating.getSelectedItem());
                if (!enterActor.getText().isEmpty()) actors.add(enterActor.getText());
                Movie go = new Movie("", year, enterDirector.getText(),
                        actors, rating, enterGenre.getText(), false);

                //Debug
                System.out.println("STARTING SEARCH, SEARCH PARAMETERS LISTED BELOW:");
                System.out.println(go.toString());

                //Search
                if (enableFuzzySearch.isSelected()) PartialSearch.getInstance().Search(go); //Fuzzy Search
                else SearchBuilder.getInstance().search(go); //Regular Search
            }
        });
        add(searchByDescription);

        //Instantiate the enable fuzzy search checkbox
        enableFuzzySearch = new JCheckBox("Enable Fuzzy Search");
        enableFuzzySearch.setBounds(20, 325, 150, 20);
        add(enableFuzzySearch);
    }

    /**
     * Driver for the MainView singleton class
     * If instance is null, create new instance
     * @return Returns the single instance of MainView
     */
    public static SearchView getInstance()
    {
        if (instance == null)
        {
            instance = new SearchView();
        }
        return instance;
    }
}
