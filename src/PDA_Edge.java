public class PDA_Edge {
    private Integer startState;
    private StackAction stackAction;
    private String stackUpdate;
    private Integer destState;

    public PDA_Edge (Integer startState, StackAction stackAction, String stackUpdate, Integer destState) {
        this.startState = startState;
        this.stackAction = stackAction;
        this.stackUpdate = stackUpdate;
        this.destState = destState;
    }

}
