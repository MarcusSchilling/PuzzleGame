package util;

import javax.swing.*;
import java.util.List;

public class Array {

    //http://stackoverflow.com/questions/8422374/java-multi-dimensional-array-transposing

    public static JLabel[][] transpose(JLabel[][] array) {
        if (array == null || array.length == 0)//empty or unset array, nothing do to here
            return array;

        int width = array.length;
        int height = array[0].length;

        JLabel[][] array_new = new JLabel[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = array[x][y];
            }
        }
        return array_new;
    }

    public static JLabel[] twoDimensionIntoOne(JLabel[][] labels) {
        JLabel[] transformedLabels = new JLabel[sizeArray(labels)];
        int count = 0;
        for (JLabel[] lab : labels) {
            for (JLabel label : lab) {
                transformedLabels[count] = label;
                count++;
            }
        }

        return transformedLabels;
    }

    private static int sizeArray(JLabel[][] labels) {
        int count = 0;
        for (JLabel[] la : labels) {
            count += la.length;
        }
        return count;
    }

    public static JButton[] listToArray(List<JButton> buttons) {
        JButton[] butt = new JButton[buttons.size()];
        int count = 0;
        for (JButton jButton : buttons) {
            butt[count] = jButton;
            count++;
        }
        return butt;
    }

}
