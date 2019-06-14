/**
 * Project: I'm a Big Kid Now (Avery, Carmen, Mehek)
 * 
 * The ideal user of this program is the parent of a child.
 * This program explains to the parent what the purpose
 * of our Child Linguistic Analysis program is. It also asks
 * the parent what age the their child is at.
 *
 * @author Mehek Khandelwal
 * @version May 16, 2019
 */

import javax.swing.JFrame;

public class LinguisticAnalysisGUI
{
    /**
     * Driver class for GUI. Displays graphical interactive
     * interface for the Child Linguistic Analysis program.
     *
     * @param  args         takes user-inputted command-line args; not used here
     */
    public static void main(String[] args)
    {
        // creates and shows a Frame 
        JFrame frame = new JFrame("Child Linguistic Analysis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a panel, and add it to the frame
        LinguisticPanel panel = new LinguisticPanel();
        frame.getContentPane().add(panel);
        
        //show frame
        frame.pack();
        frame.setVisible(true);
    }
}
