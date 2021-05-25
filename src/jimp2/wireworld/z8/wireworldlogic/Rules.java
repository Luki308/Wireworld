package jimp2.wireworld.z8.wireworldlogic;


public class Rules {

    private int[] nearbyHeadsToBecomeHead;

    public Rules (int []tabRules)
    {
        nearbyHeadsToBecomeHead=tabRules;
    }

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

    public void apply(Cell cell, World world) {
        if (cell.getState()==State.HEAD)
            headRule(cell);
        else if (cell.getState()==State.TAIL)
            tailRule(cell);
        else if(cell.getState()==State.CONDUCTOR)
            apply(cell, cell.countNeighbouringHeads(world));
    }

    public void apply(Cell cell, int neighbouringHeads) {
            conductorRule(cell,neighbouringHeads);
    }

}