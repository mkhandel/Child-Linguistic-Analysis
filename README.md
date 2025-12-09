We expect the user to be able to input a child’s age and 15 yes or no answers to whether the child
can say 15 words. The 15 words we choose to ask about are age­specific. Our program will
prompt the user through BlueJ’s command line for the age of the child and the yes or no answers.
Based on what the user inputs, we will return 2 values: the world’s average and the child’s
average for being able to speak those 15 words. The main point of our project will be to show
parents where their child stands linguistically in comparison to the average child in the United
States.

<img width="594" height="275" alt="Screenshot 2025-12-09 at 9 48 59 AM" src="https://github.com/user-attachments/assets/359668d9-5827-4c12-89b7-a5461eefd4ce" />


<img width="594" height="369" alt="Screenshot 2025-12-09 at 9 49 29 AM" src="https://github.com/user-attachments/assets/89636832-be63-401d-ba23-00c3ac944db4" />

<img width="629" height="365" alt="Screenshot 2025-12-09 at 9 49 47 AM" src="https://github.com/user-attachments/assets/3a0704f3-0293-4267-a357-2c6dc665aad7" />

Technical Report
We will be using the following ADTs for the following reasons:
● Hashtable<Integer, WordAndFreqStorage> to store our big data set containing the words
children are able to say at a given age and the frequency that they’re able to say each of
those words at
○ the keys will be the ages, the valued will be the sample 22 words we can ask the
parents to tell us if their kids can say
● LinkedList<String> to store a variable amount of words at a given frequency and age
● BinarySearchTree<String>
We will have five classes. Our first two classes are pre­computational classes.
The class WordAndFreqStorage would contain information related to a specific age. This
information is a linked list of words that children of a certain age can say at a certain frequency
and an integer variable that stores what that particular frequency is. WordAndFreqStorage
objects will be stored in a hash table in the Children class. The Children class represents all the
data of the children captured in our raw dataset. This hash table contains ages as keys and
WordAndFreqStorage objects as values. The hash table will be initialized in the Children
constructor. The get15Words method in the Children class will be able to find 15 words of a
predetermined frequency (we are still debating what that should be) by indexing the hash table
at a certain age, then looking through the WordAndFreqStorage objects to find the one with the
correct predetermined frequency. We will try to find 15 words with the predetermined frequency,
but if we cannot find enough words with that frequency, we will use a while loop to find the next
closest frequency.
The next class we have is the parent input class Child. This class has a method called
getAge that gets the child’s age from the parents. Using this age, another method called
retrieve15Words stores the information returned from the get15Words method in the Children
class. We will turn this collection of words into a queue. We are using a queue so it can be
easily iterated through to ask the parents if their child can say the word or not. We will have
another method called wordQueueToFreqArray that asks the parents whether their child can say
the 15 words or not. For each word the parent will put in yes or no. We will store a frequency of
1 in the array if the parents answers yes and 0 if no. This will be useful in the next class.
The next class is a computing class Averages. It will take the information we gathered in
the pre­computational “Children” and parent input “Child” classes and calculate two average
frequencies. The first main action method will take the the arrays from parent input and compute
an average frequency for the 15 sample words.
The next method called compute child is a private class that takes the specific child’s
frequencies (gathered in getFreqArray in parent input class) and calculate the average.
We will compare the specific child’s average to the general average of the sample words
in the last class, called “ChildLinguisticAnalysis.
” These two averages will be reported back to
the parent to give them an idea of where their child stands in their linguistic development. This
driver class will only have a main method that runs and tests the program.
HashTable
● Decision tree and categories of words, 22 paragraphs, our program decides which
paragraph to give to the user based on what their child can say, on an expert decision
tree, categorize children’s vocabulary
<img width="701" height="484" alt="Screenshot 2025-12-09 at 9 50 36 AM" src="https://github.com/user-attachments/assets/2db58522-244d-4fcd-ba23-0d69e2864d1b" />
<img width="317" height="540" alt="Screenshot 2025-12-09 at 9 50 57 AM" src="https://github.com/user-attachments/assets/c577c9f0-1e24-4585-ae2b-8e682e8a5156" />

