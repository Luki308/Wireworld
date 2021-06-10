package jimp2.wireworld.z8.datamangment;

import java.util.ArrayList;

public enum Orientation {
    NORTH,
    WEST,
    EAST,
    SOUTH,
    NORTH_WEST,
    NORTH_EAST,
    SOUTH_WEST,
    SOUTH_EAST;


    public static Orientation getOrientation(String orientation, boolean onlyStandardDirections){

        for (Orientation o : values())
            if (o.toString().equals(orientation) && directionCheck(orientation, onlyStandardDirections))
                return o;

        return null;
    }

    private static boolean directionCheck(String orientation, boolean onlyStandardDirections) {
        boolean isDirectionStandard = orientation.indexOf('_') == -1;
        return !onlyStandardDirections || isDirectionStandard;
    }

    public static Orientation[] getStandardDirections() {
        ArrayList<Orientation> standardDirections = new ArrayList<>();

        for (Orientation orientation : values())
            if (directionCheck(orientation.name(), true))
                standardDirections.add(orientation);

        Orientation[] returnedOrientations = new Orientation[standardDirections.size()];

        return standardDirections.toArray(returnedOrientations);
    }
}