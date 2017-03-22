package picture.design;

import picture.Playground;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static picture.Constant.*;

public class Pictures {

    public final static JLabel[][] fields = new JLabel[rows][cols];
    private Playground playground;

    public Pictures(Playground playground) {
        this.playground = playground;
    }

    public void addPlayingField() {
        int horizontalPosition = 0;
        int verticalPosition = 0;
        for (JLabel[] jLabels : fields) {
            for (JLabel jLabel : jLabels) {
                jLabel = new JLabel("");
                jLabel.setBounds(positionFields(horizontalPosition, verticalPosition));
                fields[verticalPosition][horizontalPosition] = jLabel;
                horizontalPosition++;
            }
            horizontalPosition = 0;
            verticalPosition++;
        }

        playground.drawToContentPane(fields);
    }

    private Rectangle positionFields(double horizontalPosition, double verticalPosition) {
        Rectangle rec = new Rectangle((int) x(horizontalPosition), (int) y(verticalPosition),
                (widthField / cols), (heightField / rows));
        return rec;
    }

    private double x(double horizontalPosition) {
        double x;
        if (horizontalPosition == 0) {
            x = minLeftPositionField;
        } else {
            x = minLeftPositionField +
                    ((double) (horizontalPosition) / (cols)) *
                            ((maxLeftPositionField) - minLeftPositionField);
        }
        return x;
    }

    private double y(double verticalPosition) {
        double y;
        if (verticalPosition == 0) {
            y = minTopPositionField;
        } else {
            y = minTopPositionField +
                    (((double) ((verticalPosition) / (rows))) * ((maxTopPositionField) - minTopPositionField));
        }
        return y;
    }

    public void actualizeImages(List<ImageIcon> images) {
        int count = 0;
        for (JLabel[] arrayLabel : fields) {
            for (JLabel jLabel : arrayLabel) {
                jLabel.setIcon(images.get(count));
                count++;
            }
        }
        assert count == (cols * rows);
    }

    public void makeFieldsInvisible() {
        for (JLabel[] jLabels : fields) {
            for (JLabel jLabel : jLabels) {
                jLabel.setVisible(false);
            }
        }
    }

    public void makeFieldsVisible() {
        for (JLabel[] jLabels : fields) {
            for (JLabel jLabel : jLabels) {
                jLabel.setVisible(true);
            }
        }
    }

}
