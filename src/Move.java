public class Move {
    private Cell target;
    private String action;

    public Move(Cell t, String a) {
        this.target = t;
        this.action = a;
    }

    public Cell getTarget() {
        return target;
    }

    public String getAction() {
        return action;
    }
}
