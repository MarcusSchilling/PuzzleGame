package logic;

import util.Array;

import javax.swing.*;

import static picture.design.Pictures.fields;

public class ManipulateField {

    public static void exchangeIcons(JLabel[] labels, Direction direction) {
        switch (direction) {
            case LEFT:
                exchangeLeft(labels);
                break;
            case RIGHT:
                exchangeRight(labels);
                break;
            case DOWN:
                exchangeRight(labels);
                Array.transpose(fields);
                break;
            case UP:
                exchangeLeft(labels);
                Array.transpose(fields);
                break;
        }
    }

    public static JLabel[][] exchangeIconsVirtual(JLabel[][] labels, int rowCol, Direction direction) {
        switch (direction) {
            case LEFT: {
                JLabel[] jLabels = exchangeLeft(labels[rowCol]);
                JLabel[][] clone = labels.clone();
                clone[rowCol] = jLabels;
                return clone;
            }
            case RIGHT: {
                JLabel[] jLabels = exchangeRight(labels[rowCol]);
                JLabel[][] clone = labels.clone();
                clone[rowCol] = jLabels;
                return clone;
            }
            case DOWN: {
                JLabel[][] transpose = Array.transpose(labels);
                JLabel[] jLabels = exchangeRight(labels[rowCol]);
                JLabel[][] clone = transpose.clone();
                clone[rowCol] = jLabels;
                Array.transpose(labels);
                return Array.transpose(clone);
            }
            case UP: {
                JLabel[][] transpose = Array.transpose(labels);
                JLabel[] jLabels = exchangeLeft(labels[rowCol]);
                JLabel[][] clone = transpose.clone();
                clone[rowCol] = jLabels;
                Array.transpose(labels);
                return Array.transpose(clone);
            }
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
