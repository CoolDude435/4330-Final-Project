import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PDA_Visulization {
    private PDA pda;
    private String graphName;
    private ArrayList<String> gvHeader;
    private ArrayList<String> gvEdges;

    public PDA_Visulization(PDA pda, String graphName) {
        this.pda = pda;
        this.graphName = graphName.replaceAll("\\s", "");
    }

    public boolean createGraphFile() {
        String currentDir = System.getProperty("user.dir");
        File gvFile = new File(currentDir + "\\" + this.graphName + ".gv");
        if (this.gvHeader == null) createGVHeader();
        if (this.gvEdges == null) createGVEdges();
        ArrayList<String> headerLines = this.gvHeader;
        ArrayList<String> fileLines = this.gvEdges;
        String fileLastLine = "}";

        try {
            gvFile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error: File already exists/can't create file");
            e.printStackTrace();
            return false;
        }
        try {
            FileWriter fw = new FileWriter(gvFile);
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
            fw.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error: Could not write to file");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> createGVHeader() {
        if (this.gvHeader == null) {
            ArrayList<String> headerLines = new ArrayList<String>();
            headerLines.add("digraph " + this.graphName + " {");
            headerLines.add("rankdir = LR;");
            headerLines.add("hidden [shape = plaintext, label = \"\"];");
            headerLines.add("node [shape = doublecircle];");
            headerLines.add(this.pda.getFinalState()+";");
            headerLines.add("node [shape = circle];");
            headerLines.add("hidden -> 0;");
            this.gvHeader = headerLines;
            return headerLines;
        }
        return this.gvHeader;
    }

    public ArrayList<String> createGVEdges() {
        if (this.gvEdges == null) {
            ArrayList<String> fileLines = new ArrayList<String>();
            HashMap<String, PDA_Edge> edgeMap = this.pda.getEdgeMap();
            Set<String> inputs = edgeMap.keySet();
            ArrayList<PDA_Edge> epsilonTransitions = this.pda.getEpsilonTransitions();

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
            this.gvEdges = fileLines;
            return fileLines;
        }
        return this.createGVEdges();
    }


}



