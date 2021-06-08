package jimp2.wireworld.z8.wireworldlogic;


public enum State {
    EMPTY,
    CONDUCTOR,
    HEAD,
    TAIL;


    public static State getState(String stateName){

        for (State state : values())
            if (state.toString().equals(stateName))
                return state;
        return null;
    }
}