
/**
 *  Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * @author Carmen Chan
 * @version May 16, 2019
 * Write a description of class ChildLinguisticAnalysis here.
 * 
 * We run this program by creating 3 objects. A Children object is created to 
 * compare the user’s child (instance of the Child class). Therefore a Child 
 * object must also be created. Lastly, an Averages object is created to 
 * calculate the averages of both the Child and Children frequencies 
 * (how many of the 15 words we chose they can say). We call the 
 * computeChildAvg and computeChildrenAvg methods on the Averages object 
 * which takes both the Child and Children object in as parameters. 
 * 

 */

import java.util.Scanner;
public class ChildLinguisticAnalysis
{
    /**
     * A driver class that will test the methods in the other classes of this
     * project1
     *
     */
    public static void main(String[] args) {
        // System.out.println("pre children");
        Children children = new Children("wordbank_by_word_english_US.csv", 680);
        //System.out.println("post children");
        Scanner scan = new Scanner(System.in);
        System.out.println("Please type in the age of your child in months.");
        System.out.println("Age must be between 16 and 30 months.");
        int age = scan.nextInt();
        Child child = new Child(age);
        System.out.println("Have the frequencies printed yet?");
        Averages avg = new Averages(child, children);
        
        
        double childAvg = avg.computeChildAvg();
        double childrenAvg = avg.computeChildrenAvg();
        /*System.out.println("age" + age);
        System.out.println("childwordqueue" + child.getChildFreq());
        System.out.println("driverchildAVerages: " + avg.childAverages);*/
        System.out.println("Your child's average score for the 15 words is: " 
            + childAvg +". \n The national average is: " + 
            childrenAvg);
        //Averages tester = new Averages(Array<int> childFrequencies, Array<int> childrenFrequencies) ;

        LinguisticExpert expert = new LinguisticExpert();
        String advice;
        if (childAvg > childrenAvg) {//child average higher than national average for 15 words
            advice = expert.diagnose(age, true);
        } else {
            advice = expert.diagnose(age, false);
        }
        System.out.println("\n\nHere's what we advise you to do:");
        System.out.println(advice);
    }
}
