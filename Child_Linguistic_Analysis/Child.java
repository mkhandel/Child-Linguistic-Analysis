import java.util.Hashtable;
import java.util.Scanner;
/**
 * 
 * Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * @author (Carmen Chan)
 * @version (a version number or a date)
 * 
 * Write a description of class Child here.
 * 
 * This class gets information from the user. In the method 
 * createArrayOfFreq(age) uses the words 
 * found in Children for a given age and populates an array with whether 
 * the specific child can say that word. It uses a Scanner to ask and 
 * save an age of the child. Using this it creates a Children object that
 * has an array of the words closest to the average (0.5) of children 
 * being able to say it. It will then ask the parents whether their child 
 * can say it and save their answers in an array that is an instance 
 * variable of the class. We have a getter method to get a hold of this 
 * variable later from different classes. 
 */
public class Child
{
    // instance variables - replace the example below with your own
    private int age;
    private int percentile;
    String[] sampleWords;
    double[] sampleFreq;
    double[] childFreq = new double[15];
    /**
     * Constructor for objects of class Child
     * @param - int a
     */
    public Child(int a)
    {
        age = a;
    }

    /**
     * getAge() - getter method for user's age input
     * @return - int age
     */
    public int getAge() {
        return age;
    }
    /*
    public 

    public Hashtable<T, T> retrieve22Words(Children child) {
    Queue<T> storage = new Queue<T>();
    storage.push(child.getWords());

    }
     */

    /**
     * wordQueueToFreqArray(Scanner scan) 
     * @param - a Scanner 
     * @return - return an array of doubles representing whether a child
     * can say a word or not. If they can say it, a 1 is stored, if not, a 0
     * is stored. We use an averages class to get the average of these values
     * to determine any given child's average which we will compare to Children's
     * average.
     */
    public void createArrayOfFreq(int age) {
        //int age = scan.nextInt();
        //System.out.println("pre make Children object");        
        Children agedChild = new Children("wordbank_by_word_english_US.csv", 680);
        //System.out.println("pre populate");
        agedChild.find15Words(age);
        //System.out.println("post populate");
        sampleWords = agedChild.getWords15Array();
        sampleFreq = agedChild.getFreq15Array();

        //System.out.println("pre scanner");
        Scanner scan = new Scanner(System.in);
        //System.out.println("post scanner");        
        for (int i = 0; i < sampleWords.length; ++i) {
            // System.out.println("outside the if");
            System.out.println("Can your child say the word '" + 
                sampleWords[i]+"'? (y/n): ");
            String canSay = scan.next();

            if (canSay.equals("y") ) {
                childFreq[i] = 1; //value at index i of childFreq array is set to canSay
                //System.out.pritnln("Child can say this word.");
            } else if (canSay.equals("n")) { 
                childFreq[i] = 0;
                //System.out.pritnln("Child cannot say this word.");
            } else { 
                System.out.println("Invalid input.");
            }
        }
        // for (int i = 0; i < sampleFreq.length; ++i) {
            // System.out.println("childFreq: " + childFreq[i]);
        // }
    }

    /**
     * getChildFreq() - getter method for the array of child frequencies
     * @return - the childFreq array instance variable
     */
    public double[] getChildFreq() {
        return childFreq;
    }
}

