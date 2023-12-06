public class PDA_Edge {
    private Integer startState;
    private String input;
    private StackAction stackAction;
    private String stackUpdate;
    private Integer destState;

    public PDA_Edge (Integer startState, StackAction stackAction, String stackUpdate, Integer destState) {
        this.startState = startState;
        this.stackAction = stackAction;
        this.stackUpdate = stackUpdate;
        this.destState = destState;
        this.input = "";
    }

    public PDA_Edge (String input, Integer startState, StackAction stackAction, String stackUpdate, Integer destState) {
        this.input = input;
        this.startState = startState;
        this.stackAction = stackAction;
        this.stackUpdate = stackUpdate;
        this.destState = destState;
    }

    public String getInput() {
        return this.input;
    }

    public Integer getStartState() {
        return this.startState;
    }

    public StackAction getStackAction() {
        return this.stackAction;
    }

    public String getStackUpdate() {
        return this.stackUpdate;
    }

    public Integer getDestState() {
        return this.destState;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PDA_Edge) {
            PDA_Edge edge = (PDA_Edge) o;
            boolean equalStartState = this.startState == edge.getStartState();
            boolean equalStackAction = this.stackAction == edge.getStackAction();
            boolean equalStackUpdate = this.stackUpdate.equals(edge.getStackUpdate());
            boolean equalDestState = this.destState == edge.getDestState();
            if (equalStartState && equalStackAction && equalStackUpdate && equalDestState) {
                return true;
            }
        }
        return false;
    }
}
