import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

public class ResultsView extends JPanel implements ListSelectionListener {

    String titles[];
    static JList results;
    static JTextArea movieInfo;

    public ResultsView()
    {
        setSize(500, 400);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setVisible(true);
    }

    public void showMoviesText(ArrayList<String> movies)
    {

        ResultsView info = new ResultsView();

        String titles[] = new String[movies.size()];

        for (int i = 0; i < movies.size(); i++) {
            titles[i] = movies.get(i);
        }

        results = new JList(titles);
        add(results);
        results.setSelectedIndex(0);
        results.addListSelectionListener(info);

        movieInfo = new JTextArea();
        add(movieInfo);
        movieInfo.setText((String) results.getSelectedValue());

    }

    //this function needs to display rest of movie info for the selected movie, but atm I don't know the best way to get that from FilmFinder
    public void valueChanged(ListSelectionEvent event)
    {
        movieInfo.setText((String) results.getSelectedValue());
    }


}
