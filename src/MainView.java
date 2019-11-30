import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame
{
    private static MainView instance;
    private JLabel title;


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
     */
    private MainView()
    {
        setTitle("Film Finder");
        setLayout(null);

        title = new JLabel("Film Finder");
        title.setBounds(125,30, 200, 30);
        add(title);

        add(SearchView.getInstance());
        add(ResultsView.getInstance());

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
