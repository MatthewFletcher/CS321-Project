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

    private JLabel directorLabel;
    private JTextField enterDirector;
    private JLabel yearLabel;
    private JComboBox enterYear;
    private JLabel genreLabel;
    private JTextField enterGenre;
    private JLabel actorLabel;
    private JTextField enterActor;
    private JButton searchByDescription;

    private SearchView()
    {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setBounds(5,100, 267, 358);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));



        //Searching by title
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(20, 60, 80, 20);
        add(titleLabel);
        enterTitle = new JTextField("");
        enterTitle.setBounds(55, 60, 185, 20);
        add(enterTitle);
        searchByTitle = new JButton("Search by Title");
        searchByTitle.setBounds(20, 90, 220, 20);
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
        directorLabel = new JLabel("Director:");
        directorLabel.setBounds(20, 180, 80, 20);
        add(directorLabel);
        enterDirector = new JTextField("");
        enterDirector.setBounds(75, 180, 165, 20);
        add(enterDirector);
        yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 210, 80, 20);
        add(yearLabel);
        String[] yearArray = new String[133];
        for (int i = 0; i < 132; i ++) {
            yearArray[i] = Integer.toString(i + 1888);
        }
        yearArray[132] = "Any";
        enterYear = new JComboBox(yearArray);
        enterYear.setSelectedIndex(132);
        enterYear.setBounds(55, 210, 185, 20);
        add(enterYear);
        genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(20, 240, 80, 20);
        add(genreLabel);
        enterGenre = new JTextField("");
        enterGenre.setBounds(65, 240, 175, 20);
        add(enterGenre);
        actorLabel = new JLabel("Actor:");
        actorLabel.setBounds(20, 270, 80, 20);
        add(actorLabel);
        enterActor = new JTextField("");
        enterActor.setBounds(62, 270, 178, 20);
        add(enterActor);
        searchByDescription = new JButton("Search by Description");
        searchByDescription.setBounds(20, 300, 220, 20);
        searchByDescription.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                int year = -1;
                if (enterYear.getSelectedItem() != "Any") year = Integer.parseInt((String)enterYear.getSelectedItem());
                ArrayList<String> actors = new ArrayList<String>();
                actors.add(enterActor.getText());
                Movie go = new Movie("", year, enterDirector.getText(),
                        actors, -1d, enterGenre.getText(), false);
                System.out.println("STARTING SEARCH, SEARCH PARAMETERS LISTED BELOW:");
                System.out.println(go.toString()); //debug
                SearchBuilder.getInstance().search(go);
            }
        });
        add(searchByDescription);
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
