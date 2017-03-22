package logic;

import util.Array;

import javax.swing.*;

import static picture.design.Pictures.fields;

public class ManipulateField {

    public static void exchangeIcons(JLabel[] labels, Direction direction) {
        if (direction == Direction.LEFT) {
            exchangeLeft(labels);
        } else if (direction == Direction.RIGHT) {
            exchangeRight(labels);
        } else if (direction == Direction.DOWN) {
            exchangeRight(labels);
            Array.transpose(fields);
        } else if (direction == Direction.UP) {
            exchangeLeft(labels);
            Array.transpose(fields);
        }
    }

    public static JLabel[][] exchangeIconsVirtual(JLabel[][] labels, int rowCol, Direction direction) {
        if (direction == Direction.LEFT) {
            exchangeLeft(labels[rowCol]);
        } else if (direction == Direction.RIGHT) {
            exchangeRight(labels[rowCol]);
        } else if (direction == Direction.DOWN) {
            Array.transpose(labels);
            exchangeRight(labels[rowCol]);
            Array.transpose(labels);
        } else if (direction == Direction.UP) {
            Array.transpose(labels);
            exchangeLeft(labels[rowCol]);
            Array.transpose(labels);
        }
        return labels;
    }

    private static JLabel[] exchangeLeft(JLabel[] labels) {
        Icon icon = labels[0].getIcon();
        for (int i = 0; i < labels.length - 1; i++) {
            labels[i].setIcon(labels[i + 1].getIcon());
        }
        labels[labels.length - 1].setIcon(icon);
        return labels;
    }

    private static JLabel[] exchangeRight(JLabel[] labels) {
        Icon icon = labels[labels.length - 1].getIcon();
        for (int i = labels.length - 1; i > 0; i--) {
            labels[i].setIcon(labels[i - 1].getIcon());
        }
        labels[0].setIcon(icon);
        return labels;
    }

}
