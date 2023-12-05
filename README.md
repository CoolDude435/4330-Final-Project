# 4330-Final-Project
**Project Detail**
<br>
I did my project on option two Algorithms on CFGs and PDAs
___
**Author**
<br>
Justin Lin
___
**Tooling**
<br>
Programming Language: Java
<br>
IDE: VSCode
<br>
Libraries: 
- java.util (ArrayList, Set, HashSet, HashMap, TimeUnit) - basic data structures
- org.junit.jupiter.api (Assertions, BeforeEach, Test, Timeout) - JUnit 5 Testing
- java.io (File, FileWriter, IOException) - Java IO to create and write to files
___
**Sources**
<br>
*CFG.java* - Class for the CFG datatype containing methods to convert to PDA and to CNF
<br>
*CFGTest.java* - Class to test CFG.java methods
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
___
**Required Functions**
<br>
1. Define a datatype for representing context-free grammars
- public CFG(Set<String\> nonTerminals, Set<String\> terminals, ArrayList<Production\> productions, String startSymbol) : constructor for a CFG
- various getter methods
2. Define a datatype for representing push-down automata
- public PDA (Set<Integer\> states, Set<String\> inputAlphabet, Set<String\> stackAlphabet, HashMap<String, PDA_Edge> edgeMap, ArrayList<PDA_Edge> epsilonTransitions, Integer startState, Integer finalState) : constructor for a PDA
- various getter methods
- equals(Object o) : used to compare equality between two PDAs
3. Write a printing function that can generate a GraphViz file for the transition diagram of a PDA
- public PDA_Visulization(PDA pda, String graphName) : constructor for the graphViz printer
- public boolean createGraphFile() : creates a graphViz file from the PDA with the use of the helper methods below
- public ArrayList<String> createGVHeader() : creates the header of the graphViz file, based on the example graphViz file given
<br>
*Note:* it successfully creates the file on my PC on Windows10 I can't guarantee it will create the file correctly on other devices, the lines that should be printed to the file are correct though
- public ArrayList<String> createGVEdges() : creates the edges of the PDA graph with their labels
4. Write a function that can translate a CFG into a PDA
- public PDA convertToPDA() : converts a CFG to a PDA using the helper methods below
- public Set<Integer\> createStates() : create the list of states for the PDA
- public Set<String\> createInputAlphabet() : create the inputAlphabet for the PDA
- public Set<String> createStackAlphabet() : create the stackAlphabet for the PDA
- public HashMap<String, PDA_Edge> createEdgeMap() : create the edges for terminals/empty productions for the PDA
- public ArrayList<PDA_Edge> createEpsilonTransitions() : create the epsilon transitions for the PDA; these are the default edges and edges from the CFG productions
- public ArrayList<PDA_Edge> createDefaultEdges() : create the default edges like the edge to the final state, starting symbol edge, and stacksymbol edge
- public Integer createStartState() : create the startState of the PDA; should always be 0 by default
- public Integer createFinalState() : create the startState of the PDA; should always be 3 by default
5. Write a function to transform a grammar into Chomsky normal form
- public void convertToCNF() : converts a CFG to CNF using the helper methods below
- public void removeUnitProds() : removes unit productions
- public void removeEmptyProds() : removes empty productions
- private static ArrayList<Production\> produceProdsWithoutEmpties(Production prodToCheck, Integer index, Set<String\> emptyProds) : produces all combinations of a production with the empty productions removed
- public void replaceTerminals() : replaces terminals with a standin nonterminal
- public void splitProds() : split up productions to have a max length of two
- private static boolean hasAnEmptyProd(Production prod, Set<String\> emptyProds) : checks if an production has an empty production
- private String createNewNonTerminal() : creates a new nonterminal
- private static String duplicateLetter(Integer numDuplicates, String letter) : helps the method above; duplicates a letter a number of times
___
**Status**
<br>
Currently I have not found any bugs yet, everything seems to be in working order.
___
**Testing**
<br>
___