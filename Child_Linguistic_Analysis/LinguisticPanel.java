/**
 * Project: I'm a Big Kid Now (Avery, Carmen, Mehek)
 * 
 * This program instantiates three sub-panels that
 * get connected to a main panel that the user will
 * interact with.
 *
 * @author Mehek Khandelwal
 * @version May 16, 2019
 */

//import statements
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LinguisticPanel extends JPanel {
    /**
     * Constructor. Initializes 3 sub-panels that make up
     * this panel. Also designs this panel.
     */
    public LinguisticPanel() {
        this.setLayout(new BorderLayout(10, 10)); // hgap, vgap
        this.setBackground(Color.blue); // to match the background color of center grid panel
        
        //add the mini-panels
        this.add(makeNorthPanel(), BorderLayout.NORTH);
        this.add(makeCenterPanel(), BorderLayout.CENTER);
        this.add(makeSouthPanel(), BorderLayout.SOUTH);
    }

    /**
     * This panel displays text that tells the user what the
     * name of the program is.
     *
     * @return      a panel that contains JLabel and JPanel objects
     */
    private JPanel makeNorthPanel() {
        //design and label the title panel
        JLabel titleLabel = new JLabel("I'm a big kid now!", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif",Font.ITALIC, 40));
        titleLabel.setForeground(Color.white);

        // create northPanel using default FlowLayout manager
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.blue);
        northPanel.add(titleLabel);
        return northPanel;
    }

    /**
     * This panel displays text that tells the user the purpose
     * of the program. It also displays the text asking the user
     * what the age of their child is.
     *
     * @return      a panel that contains JComboBox and JPanel objects
     */
    private JPanel makeSouthPanel() {
        // create southPanel using default FlowLayout manager
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.blue);

        //the combo box
        // Create the list of strings for the combo box options
        String[] possibleAges = {"Select your child's age...", "16",
                "17", "18", "19", "20", "21", "22","23", "24",
                "25", "26", "27","28", "29", "30"};
        JComboBox ageCombo = new JComboBox (possibleAges);
        ageCombo.setBackground (Color.cyan);

        //add the combo box to the panel
        southPanel.add(ageCombo);

        return southPanel;
    }

    /**
     * This panel displays a paragraph that tells the user the purpose
     * of the program.
     *
     * @return      a panel that contains JLabel and JPanel objects
     */
    private JPanel makeCenterPanel () {
        //write text to display in this panel
        String line1 = "Ever wonder whether or not your child has a good handle on vocabulary?";
        String line2 = "We’re here to help with a linguistic survey and personalized advice!";
        String line3 = "DISCLAIMER: This tool should be used as a benchmark for where";
        String line4 = "a child is at compared to a general average. By no means is this";
        String line5 = "a diagnostic tool, or meant to be passing judgement";
        String line6 = "on your child's abilities.";

        //create labels for each line of text that makes up the advice paragraph
        JLabel introLabel1 = new JLabel(line1, JLabel.CENTER);
        introLabel1.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel1.setForeground(Color.white);

        JLabel introLabel2 = new JLabel(line2, JLabel.CENTER);
        introLabel2.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel2.setForeground(Color.white);

        JLabel introLabel3 = new JLabel(line3, JLabel.CENTER);
        introLabel3.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel3.setForeground(Color.white);

        JLabel introLabel4 = new JLabel(line4, JLabel.CENTER);
        introLabel4.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel4.setForeground(Color.white);

        JLabel introLabel5 = new JLabel(line5, JLabel.CENTER);
        introLabel5.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel5.setForeground(Color.white);

        JLabel introLabel6 = new JLabel(line6, JLabel.CENTER);
        introLabel6.setFont(new Font("Serif", Font.ITALIC,25));
        introLabel6.setForeground(Color.white);

        //set up the panel that contains all the sentence pieces
        JPanel listPanel = new JPanel();
        listPanel.add(introLabel1);
        listPanel.add(introLabel2);
        listPanel.add(introLabel3);
        listPanel.add(introLabel4);
        listPanel.add(introLabel5);
        listPanel.add(introLabel6);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.blue);


        // create centerPanel using default FlowLayout manager;
        //design centerPanel;
        //add listPanel to the centerPanel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 3, 10, 10));
        centerPanel.setBackground(Color.blue);
        centerPanel.add(listPanel);

        return centerPanel;
    }
}
