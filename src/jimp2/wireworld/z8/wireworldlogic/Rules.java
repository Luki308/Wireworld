package jimp2.wireworld.z8.wireworldlogic;


public class Rules {

    private int[] nearbyHeadsToBecomeHead;

    private void tailRule(Cell cell) {
            cell.setState(State.CONDUCTOR);
    }

    private void headRule(Cell cell) {
            cell.setState(State.TAIL);
    }

    private void conductorRule(Cell cell, int neighbouringHeads) {
        for(int i : nearbyHeadsToBecomeHead)
        {
            if(neighbouringHeads == i)
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
        else if(cell.getState()==State.CONDUCTOR)
            System.err.println("Number of neighbouring head is not transferred to function");
    }

    public void apply(Cell cell, int neighbouringHeads) {
        nearbyHeadsToBecomeHead = new int[2];
        nearbyHeadsToBecomeHead[0] = 1;
        nearbyHeadsToBecomeHead[1] = 2;
        if(cell.getState()==State.CONDUCTOR)
            conductorRule(cell,neighbouringHeads);

    }

}