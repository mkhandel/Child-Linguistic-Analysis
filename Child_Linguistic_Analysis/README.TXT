------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: I'm A Big Kid Now: Child Linguistic Analysis.


PURPOSE OF PROJECT: Tool for parents to get a diagnostic understanding of their child’s linguistic capabilities. 

-details: Program uses large dataset and user input to develop...
	a) a report containing the child’s standing in comparison to US children’s 
	linguistic data, and 
	b)tailored advice to help the child expand their vocabulary based on select 
	behavioral traits.-data Structures in Project: linked binary trees, hash tables.

-developed mock GUI.


VERSION or DATE: May 17, 2019


AUTHORS: Mehek Khandelwal, Avery Kim, Carmen Chan


USER INSTRUCTIONS: Run ChildLinguisticAnalysis.java to start.



LIST OF CLASSES AND THEIR PURPOSES:

Child: Identifies a child's ability to say 15 predetermined words.

Children: Uses hash table to store all data points regarding the age, word, and frequency variables in CSV datafile.

Row: Stores national child data for a particular datapoint. 

Reader: Converts CSV datafile into an aggregation of Row objects.

LinguisticExpert: Uses kinked binary trees to represent a simple expert system for advising parents on how to expand their child's vocabulary.

Averages: Calculates the average ability to speak predetermined words for Children and Child objects.

ChildLinguisticAnalysis: Drives our program by instantiating and using all classes listed above.

LinguisticPanel: Creates interactive panels for GUI.

LinguisticAnalysisGUI: Drives GUI.