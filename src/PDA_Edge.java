public class PDA_Edge {
    enum StackAction{
        PUSH,
        POP,
        NOTHING
    }
    private String startState;
    private StackAction stackAction;
    private String stackUpdate;
    private String destState;

    public PDA_Edge (String startState, StackAction stackAction, String stackUpdate, String destState) {
        this.startState = startState;
        this.stackAction = stackAction;
        this.stackUpdate = stackUpdate;
        this.destState = destState;
    }

}
