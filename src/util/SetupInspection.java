package util;

import static picture.Constant.*;

public class SetupInspection {

    static String errorRowColText = "the number of COLS or ROWS are too low to achieve the best gaming experience";
    static String errorText = "the degree of difficulty is out of bound so the puzzle creator will run infinitely";

    //DEGREE_OF_DIFFICULTY is to high the game can't be setup because of an infinite loop
    static {
        if (!degreeInRange() && rowsAndColsInRange()) {
            throw new Error(errorText + System.getProperty("line.seperator") + errorRowColText);
        }
        if (!rowsAndColsInRange()) {
            throw new Error(errorRowColText);
        } else if (!degreeInRange()) {
            throw new Error(errorText);
        }
    }

    public SetupInspection() {

    }

    private static boolean degreeInRange() {
        return !(DEGREE_OF_DIFFICULTY > 9 || DEGREE_OF_DIFFICULTY < 0);
    }

    private static boolean rowsAndColsInRange() {
        return !(COLS < 2 || ROWS < 2);
    }


}
