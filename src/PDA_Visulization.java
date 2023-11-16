import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.FileWriter;
import java.io.IOException;



public class PDA_Visulization {
    private PDA pda;
    private String graphName;
    //private FileWriter fw;

    public PDA_Visulization(PDA pda, String graphName) {
        this.pda = pda;
        this.graphName = graphName.replaceAll("\\s", "");
    }

    public void createGraphFile() {
        //File f = new File
        try {
            FileWriter fw = new FileWriter(this.graphName+".gv");

            ArrayList<String> headerLines = createGVHeader();
            ArrayList<String> fileLines = createGVEdges();
            String fileLastLine = "}";

            String newLine = System.lineSeparator();

            for (String line : headerLines) {
                fw.write(line);
                fw.write(newLine);
            }
            for (String line : fileLines) {
                fw.write(line);
                fw.write(newLine);
            }
            fw.write(fileLastLine);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

    private ArrayList<String> createGVHeader() {
        ArrayList<String> headerLines = new ArrayList<String>();
        headerLines.add("digraph " + this.graphName + " {");
        headerLines.add("rankdir = LR;");
        headerLines.add("hidden [shape = plaintext, label = \"\"];");
        headerLines.add("node [shape = doublecircle];");
        headerLines.add(this.pda.getFinalState()+";");
        headerLines.add("node [shape = circle];");
        headerLines.add("hidden -> 0;");
        return headerLines;
    }

    private ArrayList<String> createGVEdges() {
        ArrayList<String> fileLines = new ArrayList<String>();
        HashMap<String, PDA_Edge> edgeMap = this.pda.getEdgeMap();
        Set<String> inputs = edgeMap.keySet();
        Set<PDA_Edge> epsilonTransitions = this.pda.getEpsilonTransitions();

        for (String input : inputs) {
            PDA_Edge edge = edgeMap.get(input);
            String graphEdge = edge.getStartState() + " -> " + edge.getDestState();
            String stackAction = "";
            
            if (edge.getStackAction() == StackAction.PUSH) {
                stackAction = "+";
            }
            else {
                stackAction = "-";
            }

            String edgeLabel = " [label = \"\'" + input + "\', " + stackAction + edge.getStackUpdate() + "\"];";
            String fileLine = graphEdge + edgeLabel;
            fileLines.add(fileLine);
        }

        for (PDA_Edge edge : epsilonTransitions) {
            String graphEdge = edge.getStartState() + " -> " + edge.getDestState();
            String stackAction = "";

            if (edge.getStackAction() == StackAction.PUSH) {
                stackAction = "+";
            }
            else {
                stackAction = "-";
            }

            String edgeLabel = " [label = \". , " + stackAction + edge.getStackUpdate() + "\"];";
            String fileLine = graphEdge + edgeLabel;
            fileLines.add(fileLine);
        }

        return fileLines;
    }
}



