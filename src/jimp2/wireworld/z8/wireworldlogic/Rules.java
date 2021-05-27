package jimp2.wireworld.z8.wireworldlogic;


public class Rules {

    private final int[] nearbyHeadsToBecomeHead = {1,2};

    private void tailRule(Cell cell) {
            cell.setState(State.CONDUCTOR);
    }

    private void headRule(Cell cell) {
            cell.setState(State.TAIL);
    }

    private void conductorRule(Cell cell, int neighbouringHeads) {
        for(int eachCondition : nearbyHeadsToBecomeHead)
        {
            if(neighbouringHeads == eachCondition)
            {
                cell.setState(State.HEAD);
            }
        }
    }

    public void apply(Cell cell) {
        if (cell.getState()==State.HEAD)
            headRule(cell);
        else if (cell.getState()==State.TAIL)
            tailRule(cell);
    }

    public void apply(Cell cell, int neighbouringHeads) {
            conductorRule(cell,neighbouringHeads);
    }

}