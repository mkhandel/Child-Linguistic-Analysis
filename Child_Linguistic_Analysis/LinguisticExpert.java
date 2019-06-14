
/**
 * Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * LinguisticExpert.java
 * 
 * Represents simple expert system for advising parents
 * on how to grow their child's vocabulary.
 * 
 * @author Mehek Khandelwal
 * @version May 15, 2019
 */

import javafoundations.*;
import java.util.Scanner;
public class LinguisticExpert
{
    // declares three trees based on the three skillsets we
    //will look at to advise the parent on how to improve
    //their child's vocabulary
    LinkedBinaryTree<String> skillset_16_22, skillset_23_26, 
    skillset_27_30;

    /**
     * Constructor for objects of class LinguisticExpert.
     * Initializes the three expert trees that vary based on 
     * the child's skillset.
     */
    public LinguisticExpert()
    {
        //create answer strings
        String ans_16_22_YY = "Walk around your home with your child. "+
            "Ask them to repeat after you as you verbally identify objects "+
            "in your home.";
        String ans_16_22_YN = "Hold your child's hand as you walk them"+
            "through your home. Ask them to repeat after you as you verbally "+
            "identify objects in your home.";
        String ans_16_22_NY = "In your local park,ask you child to repeat "+
            "after you as you verbally identify objects.";
        String ans_16_22_NN = "Watch some children shows with your child" +
            " so they can hear words and see corresponding images.";
        String ans_23_26_YY = "Arrange playdates with other children so "+
            "your child can learn words through interaction.";
        String ans_23_26_YN = "Listen to kids music with your child and "+
            "point to objects when they appear in the song";
        String ans_23_26_NY = "Go for a walk and ask you child to point "+
            "to objects as you pass them.";
        String ans_23_26_NN = "Narrate what you're doing in the home so "+
            "your child is exposed to more words.";
        String ans_27_30_YY = "Invite other children over and have a tea "+
            "party. You child will enjoy the interaction and make believe.";
        String ans_27_30_YN = "Make a story with your child, asking them "+
            "for words as you go along.";
        String ans_27_30_NY = "Go outdoors and let them explore ."+
            "As they go explore different objects, say the name of " +
            "of the object.";
        String ans_27_30_NN = "Make flashcards for words your child seems "+
            "to struggle with and practice indoors.";

        //create question strings
        String firstQuestion = "Is your child talkative? ";
        String secondQuestion_left = "Does your child like the outdoors? ";
        String secondQuestion_right = 
            "Does your child like to play with other children? ";

        //turn all the answer strings into nodes!
        LinkedBinaryTree<String> node_16_22_YY = 
            new LinkedBinaryTree<String>(ans_16_22_YY);
        LinkedBinaryTree<String> node_16_22_YN = 
            new LinkedBinaryTree<String>(ans_16_22_YN);
        LinkedBinaryTree<String> node_16_22_NY = 
            new LinkedBinaryTree<String>(ans_16_22_NY);
        LinkedBinaryTree<String> node_16_22_NN = 
            new LinkedBinaryTree<String>(ans_16_22_NN);

        LinkedBinaryTree<String> node_23_26_YY = 
            new LinkedBinaryTree<String>(ans_23_26_YY);
        LinkedBinaryTree<String> node_23_26_YN = 
            new LinkedBinaryTree<String>(ans_23_26_YN);
        LinkedBinaryTree<String> node_23_26_NY = 
            new LinkedBinaryTree<String>(ans_23_26_NY);
        LinkedBinaryTree<String> node_23_26_NN = 
            new LinkedBinaryTree<String>(ans_23_26_NN);

        LinkedBinaryTree<String> node_27_30_YY = 
            new LinkedBinaryTree<String>(ans_27_30_YY);
        LinkedBinaryTree<String> node_27_30_YN = 
            new LinkedBinaryTree<String>(ans_27_30_YN);
        LinkedBinaryTree<String> node_27_30_NY = 
            new LinkedBinaryTree<String>(ans_27_30_NY);
        LinkedBinaryTree<String> node_27_30_NN = 
            new LinkedBinaryTree<String>(ans_27_30_NN);

        //turn the necessary question strings into nodes!
        LinkedBinaryTree<String> node_secondQ_left = 
            new LinkedBinaryTree<String>(secondQuestion_left);
        LinkedBinaryTree<String> node_16_secondQ_right = 
            new LinkedBinaryTree<String>(secondQuestion_right);

        //link all the question and answer nodes together to make 
        //the final three trees!
        LinkedBinaryTree<String> node_16_22_Y = 
            new LinkedBinaryTree<String>
            (secondQuestion_right, node_16_22_YN, node_16_22_YY);
        LinkedBinaryTree<String> node_16_22_N = 
            new LinkedBinaryTree<String>
            (secondQuestion_left, node_16_22_NN, node_16_22_NY);

        LinkedBinaryTree<String> node_23_26_Y = 
            new LinkedBinaryTree<String>
            (secondQuestion_right, node_23_26_YN, node_23_26_YY);
        LinkedBinaryTree<String> node_23_26_N = 
            new LinkedBinaryTree<String>
            (secondQuestion_left, node_23_26_NN, node_23_26_NY);

        LinkedBinaryTree<String> node_27_30_Y = 
            new LinkedBinaryTree<String>
            (secondQuestion_right, node_27_30_YN, node_27_30_YY);
        LinkedBinaryTree<String> node_27_30_N = 
            new LinkedBinaryTree<String>
            (secondQuestion_left, node_27_30_NN, node_27_30_NY);

        //create 3 trees for 3 different skillsets based on age
        skillset_16_22 = new LinkedBinaryTree<String>
        (firstQuestion, node_16_22_N, node_16_22_Y);
        skillset_23_26 = new LinkedBinaryTree<String>
        (firstQuestion, node_23_26_N, node_23_26_Y);
        skillset_27_30 = new LinkedBinaryTree<String>
        (firstQuestion, node_27_30_N, node_27_30_Y);
    }

    /**
     * Follows the appropriate skillset tree based on user responses. 
     * 
     * @param age       how many months old the child is
     * @param higher    whether or not the child's average is higher than
     *                  the national average. Input true if higher.
     * 
     * @return          the advice we give the user based on their responses
     *                  to the questions
     *                  
     */
    public String diagnose(int age, boolean higher)
    {
        //declare the tree that we iterate over while scanning
        //in parent input
        LinkedBinaryTree<String> tree;

        // need to scan in parent input
        Scanner scan = new Scanner(System.in);

        //if child is between 16 and 22 months and is below national percentile
        if ( age >= 16 && age <= 22 && !higher) {
            tree = this.skillset_16_22; ////work on 16-22mo skillset
        } 
        //if child is 16-22 mo and is higher than national percentile
        //or child is 23-26 mo and is lower than national percentile
        else if ( (age >= 16 && age <= 22 && !higher) || (age >= 23 && age <= 26 && !higher) ) {
            tree = this.skillset_23_26; //work on 23-26mo skillset
        } 
        //if child is 23-26 months old and is higher than national percentile
        //or child is 27-30 months old
        else {//(age >= 23 && age <= 26 && higher) || (age >= 27 && age <= 30)
            tree = this.skillset_27_30; //work on 27-30mo skillset
        }

        //

        System.out.println("\nPlease answer Y/N for the following questions:");

        //iterate through question nodes and go down the tree
        //based on what the parent inputs
        while (tree.size() > 1)
        {
            System.out.println (tree.getRootElement());
            if (scan.nextLine().equalsIgnoreCase("N"))
                tree = tree.getLeft();
            else
                tree = tree.getRight();
        }

        //return the advice at the end of the tree path
        return tree.getRootElement();
    }

    /**
     * Follows the appropriate skillset tree based on user responses. 
     * 
     * @param age       how many months old the child is
     * @param higher    whether or not the child's average is higher than
     *                  the national average. Input true if higher.
     * 
     * @return          the advice we give the user based on their responses
     *                  to the questions
     *                  
     */
    public static void main(String[] args) {
        LinguisticExpert expert = new LinguisticExpert();
        String advice = expert.diagnose(23, true);
        System.out.println("Here's what we advise you to do:");
        System.out.println(advice);
        
    }
} 