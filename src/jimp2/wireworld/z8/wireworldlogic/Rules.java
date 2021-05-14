package jimp2.wireworld.z8.wireworldlogic;


public class Rules {

    private int[] nearbyHeadsToBecomeHead;

    private void tailRule(Cell cell) {
        if(cell.getState()==State.TAIL)
            cell.setState(State.CONDUCTOR);
    }

    private void headRule(Cell cell) {
        if(cell.getState()==State.HEAD)
            cell.setState(State.TAIL);
    }

    private void conductorRule(Cell cell, int neighbouringHeads) {
        if(cell.getState()==State.CONDUCTOR)
        for(int i : nearbyHeadsToBecomeHead)
        {
            if(neighbouringHeads == i)
            {
                cell.setState(State.HEAD);
            }
        }
    }

    public void apply(Cell cell) {
        ;
    }

    public void apply(Cell cell, int neighbouringHeads) {
        // TODO implement here
    }

}