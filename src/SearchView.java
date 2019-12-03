import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchView extends JPanel {

    private static SearchView instance = null;

    private JLabel titleLabel;
    private JTextField enterTitle;
    private JButton searchByTitle;

    private JLabel ratingLabel;
    private JComboBox enterRating;
    private JLabel directorLabel;
    private JTextField enterDirector;
    private JLabel yearLabel;
    private JComboBox enterYear;
    private JLabel genreLabel;
    private JTextField enterGenre;
    private JLabel actorLabel;
    private JTextField enterActor;
    private JButton searchByDescription;

    private JCheckBox enableFuzzySearch;

    private JButton showWatchList;

    private SearchView()
    {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(5,100, 267, 358);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        //Searching by title
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 15, 80, 20);
        add(titleLabel);
        enterTitle = new JTextField("");
        enterTitle.setBounds(55, 15, 185, 20);
        add(enterTitle);
        searchByTitle = new JButton("Search by Title");
        searchByTitle.setBounds(20, 45, 220, 20);
        searchByTitle.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                Movie go = new Movie(enterTitle.getText(), -1, "",
                        new ArrayList<String>(), -1d, "", false);
                System.out.println("STARTING SEARCH, SEARCH PARAMETERS LISTED BELOW:");
                System.out.println(go.toString()); //debug
                SearchBuilder.getInstance().search(go);
            }
        });
        add(searchByTitle);




        //Searching by description
        String[] ratingArray = new String[102];
        for (int i = 0; i < 101; i++) {
            ratingArray[i + 1] = Double.toString(((double) i) / 10);
        }
        ratingArray[0] = "None";
        ratingLabel = new JLabel("Minimum Rating:");
        ratingLabel.setBounds(20, 75, 180, 20);
        add(ratingLabel);
        enterRating = new JComboBox(ratingArray);
        enterRating.setBounds(123, 75, 117, 20);
        add(enterRating);
        directorLabel = new JLabel("Director:");
        directorLabel.setBounds(20, 105, 80, 20);
        add(directorLabel);
        enterDirector = new JTextField("");
        enterDirector.setBounds(75, 105, 165, 20);
        add(enterDirector);
        yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 135, 80, 20);
        add(yearLabel);
        String[] yearArray = new String[133];
        for (int i = 0; i < 132; i ++) {
            yearArray[i] = Integer.toString(i + 1888);
        }
        yearArray[132] = "Any";
        enterYear = new JComboBox(yearArray);
        enterYear.setSelectedIndex(132);
        enterYear.setBounds(55, 135, 185, 20);
        add(enterYear);
        genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(20, 165, 80, 20);
        add(genreLabel);
        enterGenre = new JTextField("");
        enterGenre.setBounds(65, 165, 175, 20);
        add(enterGenre);
        actorLabel = new JLabel("Actor:");
        actorLabel.setBounds(20, 195, 80, 20);
        add(actorLabel);
        enterActor = new JTextField("");
        enterActor.setBounds(62, 195, 178, 20);
        add(enterActor);
        searchByDescription = new JButton("Search by Description");
        searchByDescription.setBounds(20, 225, 220, 20);
        searchByDescription.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                int year = -1;
                double rating = -1;
                ArrayList<String> actors = new ArrayList<String>();

                if (!enterYear.getSelectedItem().equals("Any")) year = Integer.parseInt((String) enterYear.getSelectedItem());
                if (!enterRating.getSelectedItem().equals("None")) rating = Double.parseDouble((String) enterRating.getSelectedItem());
                if (!enterActor.getText().isEmpty()) actors.add(enterActor.getText());

                Movie go = new Movie("", year, enterDirector.getText(),
                        actors, rating, enterGenre.getText(), false);
                System.out.println("STARTING SEARCH, SEARCH PARAMETERS LISTED BELOW:");
                System.out.println(go.toString()); //debug

                SearchBuilder.getInstance().search(go);
            }
        });
        add(searchByDescription);


        //Enable fuzzy search checkbox
        enableFuzzySearch = new JCheckBox("Enable Fuzzy Search");
        enableFuzzySearch.setBounds(20, 255, 150, 20);
        add(enableFuzzySearch);


        //Show current watchlist
        showWatchList = new JButton("Show WatchList");
        showWatchList.setBounds(20, 325, 220, 20);
        showWatchList.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        add(showWatchList);

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
