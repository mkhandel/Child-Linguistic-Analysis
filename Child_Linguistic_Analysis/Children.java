
/**
 * Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * Children reads a csv file and puts the information into useful format. It has an array where 
 * each index corresponds with a specific age. It then populates each array index with a hash table 
 * that has frequencies as keys and the words that appear at that frequency at that age as the value.
 * 
 * @author Avery Kim (jkim73)
 */
import java.util.*;
import java.lang.Object.*;
import javafoundations.*;
import java.io.IOException;
import java.text.DecimalFormat;
public class Children
{
    //initialize instance variables
    public String[] ageArray;
    public Hashtable[] dataArray;
    private Reader read;
    private Vector<Row> datasetVector;
    private static DecimalFormat df = new DecimalFormat("0.00");
    public String[] Words15Array = new String[15];
    public double[] Freq15Array = new double[15];
    
    
    /**
     * Constructor for objects of class Children.
     */
    public Children(String filename, int numRows)
    {
        try{
            //read in a file in order to get info about that a group of children can say a word,
            // that word, and they frequency they're able to say it at

            read = new Reader(filename, numRows);
            datasetVector = read.getDataset();
            
            

            //instantiate the arrays
            ageArray = new String[15];    //populate month array 
            dataArray = new Hashtable[15];
            ageArray [0] = "month.16.pct";
            ageArray [1] = "month.17.pct";
            ageArray [2] = "month.18.pct";
            ageArray [3] = "month.19.pct";
            ageArray [4] = "month.20.pct";
            ageArray [5] = "month.21.pct";
            ageArray [6] = "month.22.pct";
            ageArray [7] = "month.23.pct";
            ageArray [8] = "month.24.pct";
            ageArray [9] = "month.25.pct";
            ageArray [10] = "month.26.pct";
            ageArray [11] = "month.27.pct";
            ageArray [12] = "month.28.pct";
            ageArray [13] = "month.29.pct";
            ageArray [14] = "month.20.pct";

            //instantiating a hash table at each spot in the array
            for (int r = 0; r <15; r++){
                dataArray[r] = new Hashtable<Double, Vector<String>>();
            }

            //populate hashtables in the array
            for(int i = 0; i < numRows; i++){       //iterates through each row in the dataset 
                //that represents a datapoint for a certain
                //age, word, and frequency
                Row currentRow = datasetVector.get(i);
                String currentWord = currentRow.getDefinition();

                //each word has a specific frequency at a particular age.
                //in this loop that looks at info for one word, we visit each age
                //that the word is spoken at and find the frequency that the 
                //particular age group can say the word.
                //with these 3 datapoints, we update the hashtable
                //stored at a particular age.
                for(int n = 0; n < 15; n++){        //iterates through age
                    String currentAge = ageArray[n];
                    Hashtable<Double, Vector<String>> ageHash = dataArray[n];
                    double freq = currentRow.getDataValue(currentAge);    //gets freq from dataset
                    //System.out.println("inside loop");
                    if(!ageHash.containsKey(freq)){     //if this freq does not exist
                        //make the word vector that will be the value in the hash table
                        Vector<String> wordVector = new Vector<String>();

                        wordVector.addElement(currentWord);
                        ageHash.put(freq, wordVector);
                    }
                    else{
                        Vector<String> wordVectorAtFreq = ageHash.get(freq);
                        wordVectorAtFreq.add(currentWord);

                        ageHash.replace(freq, wordVectorAtFreq);

                    }
                }

            }
            //System.out.println("This is Children constructor");
        }catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        //System.out.println("Dataset created");
    }
    
    /**
     * find15Words(int age) takes in an age in months and returns the words at that age that have a 
     * frequency (percent of children able to say the word at that age) closest to 0.5 (the middle).
     * 
     * @param int age - takes an integer of what age we are looking for. Age in months.
     */
    public void find15Words(int age){
        final double avgFreq = 0.5;
        Hashtable<Double, Vector<String>> currentAgeHash = dataArray[age-16];
        //Hashtable<String, Double> wordsHash = new Hashtable<String, Double> (15);
        
        //System.out.println("This is get15Words before the for loops");

        boolean reached1 = false;

        double currentFreq = 0.5;
        //15 word counter = 0
        int totWordsCollected = 0;
        //while (we don't have 15 words and the freq we're looking at is less than 1)
        //System.out.println("entering the first while loop");
        while (totWordsCollected < 15 && currentFreq > -0.01) {
            //System.out.println("Number of words collected: "+ totWordsCollected);
            //System.out.println("Current word frequencyu we're looking at: "+ currentFreq);

            //information about the array of words as a particular frequency and age
            Vector<String> wordsAtThisFrequency = currentAgeHash.get(currentFreq);
            int wordsAtThisFrequency_index = 0;

            //explain 3 conditionals here
            //System.out.println("the words are: "+wordsAtThisFrequency);
            //System.out.println("");
            //System.out.println("");
            
            while ( wordsAtThisFrequency != null && wordsAtThisFrequency_index < wordsAtThisFrequency.size() 
            && totWordsCollected < 15) {
                String word = wordsAtThisFrequency.get(wordsAtThisFrequency_index);
                Words15Array[totWordsCollected] = word;
                Freq15Array[totWordsCollected] = currentFreq;
                
                //update counters
                wordsAtThisFrequency_index ++;
                totWordsCollected ++;
            }

            //look at frequencies below 0.5 if we reached 1
            if (currentFreq == 1.0) {
                currentFreq = 0.5;
                reached1 = true;
            }

            //update counter
            if (reached1) {
                currentFreq -= 0.01;
                //currentFreq = currentFreq%0.01;
            } else {
                currentFreq += 0.01;
                //currentFreq = currentFreq%0.01;
            }
            //System.out.println("nicely-formatted frequency: "+df.format(currentFreq));
            currentFreq =  Double.parseDouble(df.format(currentFreq));
        }

        
    }

    /**
     * getWords15Array() getter method for the array holding the words collected for that specific age
     * 
     * @return String[] - returns the instance variable array of the 15 collected words
     */
    public String[] getWords15Array(){
        return Words15Array;
    }
    
    /**
     * getFreq15Array() getter method for array holding the frequencies that corresponds to the 15 
     * collected words. 
     * 
     * @return double[] - returns the instance variable array of the 15 collected frequencies
     */
    public double[] getFreq15Array(){
        return Freq15Array;
    }
    
    /**
     * toString() formats how instances of this class are formated when printed
     * 
     * @return String - returns a String of how this class will be printed
     */
    public String toString(){
        String s = "";  
        s += "this is the child class: \n";
        s += "the number of rows in the Vector is: \n";
        s += datasetVector;

        return s;
    }
    
    /**
     * main(String[] args) main method for testing 
     * 
     * @param String[] args - command-line comments
     */
    public void main(String[] args){
        //Children dataset = new Children("wordbank_by_word_english_US.csv", 682);

        //System.out.println(dataset);

        //System.out.println(dataset.get15Words(17));

    }
}
