
/**
 * Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * Take the information we gathered in the pre-computational “Children” 
 * and parent input “Child” classes and calculate the average frequencies
 * of speakability for the children and child for the predetermined age.
 *
 * @author Mehek Khandelwal
 * @version May 14, 2019
 */
import javafoundations.*;
import java.util.Hashtable;
import java.util.Collection;
public class Averages
{
    // instance variables
    double[] childAverages;     //holds average frequencies for a specific child
    double[] childrenAverages;  //holds average frequencies for all children in study
    /**
     * Constructor for objects of class Averages.
     * Gets frequencies from Child and Children classes
     * that are necessary to find the average frequency of 
     * speakability for the pre-determined 15 words.
     * 
     * @param david       child object
     */
    public Averages(Child childData, Children nationalData)
    {
        //get values to calculate child average
        int age = childData.getAge();
        //System.out.println("age of child is currently: " + age);
        childData.createArrayOfFreq(age);
        //System.out.println("what ChildAverages is supposed to be: " + childData.wordQueueToFreqArray(age));
        childAverages = childData.getChildFreq();
        childrenAverages = nationalData.getFreq15Array();
    }

    /**
     * Takes the average of the frequencies stored in the 
     * childrenAverages collection.
     *
     * @return    national average of frequency for the 15 sample words
     */
    public double computeChildrenAvg()
    {
        double childrenAverage = 0.0;
        
        //adds up all the values of the frequencies
        for (int i = 0; i < childrenAverages.length; i ++) {
            childrenAverage += childrenAverages[i];
            System.out.println("national average for particular word: "+childrenAverages[i]);
            //System.out.println("average "+i+" for children :"+childrenAverage);
        }
        
        //divides by number of numbers (in order to get the average freq.)
        return childrenAverage/15.0;
    }

    /**
     * Takes the average of the frequencies stored in the 
     * childAverages array.
     *
     * @return    child's average frequency for the 15 sample words
     */
    public double computeChildAvg()
    {
        double childAverage = 0.0;
        //System.out.println("childAVerages: " + childAverages);
        for (double i : childAverages) {
            childAverage += i;
            //System.out.println("currnt average for child :"+childAverage);
        }
        
        //divides by number of numbers (in order to get the average freq.)
        return childAverage/15.0;
    }
}
