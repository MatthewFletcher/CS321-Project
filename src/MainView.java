import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Extends the JFrame interface
 * Contains the main method that runs on startup
 * Initializes the GUI menus for the application and contains them inside of its JFrame
 * Has the singleton design pattern
 */
public class MainView extends JFrame
{

    /**
     * static MainView instance- keeps track of MainView instance for Singleton implementation
     */
    private static MainView instance;

    /**
     * Main function in the application
     * Gets called first, calls relevant methods and application to make FilmFinder work
     * @param args Arguments for the main function
     */
    public static void main(String[] args)
    {
        MainView.getInstance();
        System.out.println("BUILD SUCCESS!");
    }

    /**
     * Default constructor
     * Builds the main framework for other GUI panels and assets to be placed into
     * Creates the title panel and label
     * Initializes SearchView and ResultsView
     * Uses no layout manager so GUI assets can be placed at exact coordinates
     */
    private MainView()
    {
        //Setting the JFrame settings
        setTitle("Film Finder");
        setLayout(null);

        //Initializing and placing the label that contains the title image
        //Also initializing the title image
        JLabel title = new JLabel();
        String titlePath = new String("images/TitleNoNames.png");
        ImageIcon titleIcon = new ImageIcon(titlePath);
        Image titleImage = titleIcon.getImage();
        titleImage = titleImage.getScaledInstance(267, 90, Image.SCALE_SMOOTH);
        title.setBounds(5,5, 267, 90);
        title.setIcon(new ImageIcon(titleImage)); //set the scaled poster in the JLabel
        add(title);

        //Initializes an aesthetic background panel for the title label to be placed upon
        JPanel titleBackground = new JPanel();
        titleBackground.setBackground(Color.LIGHT_GRAY);
        titleBackground.setBounds(5,5, 267, 90);
        titleBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(titleBackground);

        //Initializing ResultsView and SearchView panels
        add(SearchView.getInstance());
        add(ResultsView.getInstance());

        //Setting more JFrame settings
        setSize(800,500);//400 width and 500 height
        setVisible(true);//making the frame visible
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent arg0)
            {
                DBWizard.writeDB(FilmFinder.getInstance().getMasterList());
                System.exit(0);
            }
        });
    }

    /**
     * Driver for the MainView singleton class
     * If instance is null, create new instance
     * @return Returns the single instance of MainView
     */
    public static MainView getInstance()
    {
        if (instance == null)
        {
            instance = new MainView();
        }
        return instance;
    }
}
