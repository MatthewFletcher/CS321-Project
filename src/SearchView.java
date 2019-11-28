import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchView extends JPanel {

    private static SearchView instance = null;
    private JLabel searchView;
    private JTextField enterTitle;
    private JButton searchByTitle;
    private JTextField enterDirector;
    private JComboBox enterYear;
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

        //Searching by title
        enterTitle = new JTextField("Enter a movie title...");
        enterTitle.setBounds(20, 60, 220, 25);
        add(enterTitle);
        searchByTitle = new JButton("Search by Title");
        searchByTitle.setBounds(20, 100, 200, 25);
        searchByTitle.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                SearchBuilder.getInstance().search(new Movie(enterTitle.getText(), -1, "",
                        new ArrayList<String>(), -1d, ""));
            }
        });
        add(searchByTitle);

        //Searching by description
        enterDirector = new JTextField("");
        enterDirector.setBounds(20, 180, 220, 25);
        add(enterDirector);
        String[] yearArray = new String[133];
        for (int i = 0; i < 132; i ++) {
            yearArray[i] = Integer.toString(i + 1888);
        }
        yearArray[132] = "Any";
        enterYear = new JComboBox(yearArray);
        enterYear.setSelectedIndex(132);
        enterYear.setBounds(20, 220, 220, 25);
        add(enterYear);
        enterGenre = new JTextField("");
        enterGenre.setBounds(20, 260, 220, 25);
        add(enterGenre);
        searchByDescription = new JButton("Search by Description");
        searchByDescription.setBounds(20, 300, 200, 25);
        searchByDescription.addActionListener(new ActionListener() {
            @Override
            //Do this on button push
            public void actionPerformed(ActionEvent actionEvent) {
                int year = -1;
                if (enterYear.getSelectedItem() != "Any") year = Integer.parseInt((String)enterYear.getSelectedItem());
                SearchBuilder.getInstance().search(new Movie("", year, enterDirector.getText(),
                        new ArrayList<String>(), -1d, enterGenre.getText()));
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
