# 4330-Final-Project
**Project Detail**
<br>
I did my project on option two Algorithms on CFGs and PDAs
<br>
Extra credit: I completed the course evaluation.
___
**Author**
<br>
Justin Lin
___
**Tooling**
<br>
Programming Language: Java
<br>
IDE: VSCode Win10
<br>
VSCode Extension: I used Graphviz (dot) language support for Visual Studio Code by João Pinto to render my graphViz files inside VSCode
<br>
Libraries: 
- java.util (ArrayList, Set, HashSet, TimeUnit) - basic data structures
- org.junit.jupiter.api (Assertions, BeforeEach, Test, Timeout) - JUnit 5 Testing (I think you will need to download JUnit 5 to run tests properly)
- java.io (File, FileWriter, IOException) - Java IO to create and write to files
___
**Sources**
<br>
*CFG.java* - Class for the CFG datatype containing methods to convert to PDA and to CNF
<br>
*CFGTest.java* - Class to test CFG.java methods
<br>
*Examples.java* - Class that contains all the example CFGs and PDAs to be used in testing
<br>
*PDA_Edge.java* - datatype for the edges in PDAs
<br>
*PDA_Visualization.java* - Class for the graphViz file constructor
<br>
*PDA.java* - Class for the PDA datatype
<br>
*PDATest.java* - Class to test PDA.java methods
<br>
*Production.java* - datatype for the productions in CFGs
<br>
*StackAction.java* - enums for what happens to the stack in PDAs
<br>
*PDA_EdgeTest.java* - Class to test PDA_Edge.java methods
<br>
*ProductionTest.java* - Class to test Production.java methods
<br>
*ViewableExamples.java* - this class isn't a test class, but rather examples you can run(by running a test method) to see some functionality of my program easier some of them outputs stuff to your debug console(on VSCode) while others creates example graphViz files you can render, I use the plugin mentioned above to easily render them in VSCode
___
**Required Functions**
<br>
1. Define a datatype for representing context-free grammars
- CFG(Set<String\> nonTerminals, Set<String\> terminals, ArrayList<Production\> productions, String startSymbol) : constructor for a CFG
- various getter methods
2. Define a datatype for representing push-down automata
- PDA(Set<Integer\> states, Set<String\> inputAlphabet, Set<String\> stackAlphabet, ArrayList<PDA_Edge> edges, ArrayList<PDA_Edge> epsilonTransitions, Integer startState, Integer finalState) : constructor for a PDA
- various getter methods
- equals(Object o) : used to compare equality between two PDAs
3. Write a printing function that can generate a GraphViz file for the transition diagram of a PDA
- PDA_Visulization(PDA pda, String graphName) : constructor for the graphViz printer
- createGraphFile() : creates a graphViz file from the PDA with the use of the helper methods below
<br>
*Note:* it successfully creates the file on my PC on Windows10 I can't guarantee it will create the file correctly on other devices, the lines that should be printed to the file are correct though
- createGVHeader() : creates the header of the graphViz file, based on the example graphViz file given
- createGVEdges() : creates the edges of the PDA graph with their labels
4. Write a function that can translate a CFG into a PDA
- convertToPDA() : converts a CFG to a PDA using the helper methods below
- createStates() : create the list of states for the PDA
- createInputAlphabet() : create the inputAlphabet for the PDA
- createStackAlphabet() : create the stackAlphabet for the PDA
- createEdges() : create the edges for terminals/empty productions for the PDA
- createEpsilonTransitions() : create the epsilon transitions for the PDA; these are the default edges and edges from the CFG productions
- createDefaultEdges() : create the default edges like the edge to the final state, starting symbol edge, and stacksymbol edge
- createStartState() : create the startState of the PDA; should always be 0 by default
- createFinalState() : create the startState of the PDA; should always be 3 by default
5. Write a function to transform a grammar into Chomsky normal form
- convertToCNF() : converts a CFG to CNF using the helper methods below
- removeUnitProds() : removes unit productions, returns true if it added productions
- removeEmptyProds() : removes empty productions, returns true if it added productions
- produceProdsWithoutEmpties() : produces all combinations of a production with the empty productions removed
- findEmptyProds() : finds all the productions that outputs nothing
- findEmptyProdsNonTerms() : given a list of empty productions creates and returns a set of strings which are the nonterminals of those productions
- replaceTerminals() : replaces terminals with a standin nonterminal
- splitProds() : split up productions to have a max length of two
- hasAnEmptyProd() : checks if an production has an empty production
- createNewNonTerminal() : creates a new nonterminal
- duplicateLetter() : helps the method above; duplicates a letter a number of times
___
**Status**
<br>
After the testing I've done I don't seem to have found any issues with the examples I tested on.
<br>
If there are any issues I think it would be on converting a CFG to CNF where the CFG has productions with multiple
<br>
outputs that can lead to an empty production. IE S -> A B C, A -> " ", B -> " ", C -> " "
<br>
I've tested that exact CFG and it seems to work, but there could be edge cases I haven't found.
<br>
Another possible issue could be the PDA_Visualization which creates a graphViz file,
<br>
on my PC it creates the file, but on other devices behavior might differ, like if you have security/antivirus and stuff.
___
**Testing**
<br>
I did testing with the JUnit5 Library creating JUnit tests for all my public methods
___