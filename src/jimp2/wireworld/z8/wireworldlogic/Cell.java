package jimp2.wireworld.z8.wireworldlogic;


public class Cell {

    private State state;

    public Cell(State state) {
        this.state=state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state=state;
    }
}