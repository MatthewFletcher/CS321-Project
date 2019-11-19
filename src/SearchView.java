import javax.swing.*;
import java.awt.*;

public class SearchView extends JPanel {

    private static SearchView instance = null;
    private JLabel searchView;
    private JTextField enterTitle;
    private JButton searchByTitle;
    private JTextField enterActor;
    private JTextField enterYear;
    private JTextField enterGenre;
    private JButton searchByDescription;

    private SearchView()
    {
        setLayout(null);
        setBackground(Color.GRAY);
        setBounds(5,100, 267, 358);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        searchView = new JLabel("Search View");
        searchView.setBounds(20, 25, 220, 25);
        add(searchView);
        enterTitle = new JTextField("Enter a movie title...");
        enterTitle.setBounds(20, 60, 220, 25);
        add(enterTitle);
        searchByTitle = new JButton("Search by Title");
        searchByTitle.setBounds(20, 100, 200, 25);
        add(searchByTitle);

        enterActor = new JTextField("Enter an actor's name...");
        enterActor.setBounds(20, 180, 220, 25);
        add(enterActor);
        enterYear = new JTextField("Enter a year...");
        enterYear.setBounds(20, 220, 220, 25);
        add(enterYear);
        enterGenre = new JTextField("Enter a genre...");
        enterGenre.setBounds(20, 260, 220, 25);
        add(enterGenre);
        searchByDescription = new JButton("Search by Description");
        searchByDescription.setBounds(20, 300, 200, 25);
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
