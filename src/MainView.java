import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame
{
    private static MainView instance;
    private JPanel titleBackground;
    private JLabel title;
    private Image titleImage;


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

        //Title Stuff
        title = new JLabel();
        String titlePath = new String("images/TitleNoNames.png");
        ImageIcon titleIcon = new ImageIcon(titlePath);
        titleImage = titleIcon.getImage();
        titleImage = titleImage.getScaledInstance(267, 90, Image.SCALE_SMOOTH); //create a version of the poster scaled to the size of the JLabel
        title.setBounds(5,5, 267, 90);
        title.setIcon(new ImageIcon(titleImage)); //set the scaled poster in the JLabel
        add(title);

        //Title background stuff
        titleBackground = new JPanel();
        titleBackground.setBackground(Color.LIGHT_GRAY);
        titleBackground.setBounds(5,5, 267, 90);
        titleBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        add(titleBackground);

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
