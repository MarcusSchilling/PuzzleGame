package picture.design;

import picture.Playground;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static picture.Constant.*;

public class Pictures {

    public static final JLabel[][] fields = new JLabel[ROWS][COLS];
    private Playground playground;

    public Pictures(Playground playground) {
        this.playground = playground;
    }

    public void addPlayingField() {
        int horizontalPosition = 0;
        int verticalPosition = 0;
        for (JLabel[] jLabels : fields) {
            for (int i = 0; i < jLabels.length; i++) {
                jLabels[i] = new JLabel("");
                jLabels[i].setBounds(positionFields(horizontalPosition, verticalPosition));
                fields[verticalPosition][horizontalPosition] = jLabels[i];
                horizontalPosition++;
            }
            horizontalPosition = 0;
            verticalPosition++;
        }

        playground.drawToContentPane(fields);
    }

    private Rectangle positionFields(double horizontalPosition, double verticalPosition) {
        Rectangle rec = new Rectangle((int) x(horizontalPosition), (int) y(verticalPosition),
                (WIDTH_FIELD / COLS), (HEIGHT_FIELD / ROWS));
        return rec;
    }

    private double x(double horizontalPosition) {
        double x;
        if (horizontalPosition == 0) {
            x = MIN_LEFT_POSITION_FIELD;
        } else {
            x = MIN_LEFT_POSITION_FIELD + (horizontalPosition / (COLS)) * ((MAX_LEFT_POSITION_FIELD) - MIN_LEFT_POSITION_FIELD);
        }
        return x;
    }

    private double y(double verticalPosition) {
        double y;
        if (verticalPosition == 0) {
            y = MIN_TOP_POSITION_FIELD;
        } else {
            y = MIN_TOP_POSITION_FIELD + ((verticalPosition) / (ROWS) * ((MAX_TOP_POSITION_FIELD) - MIN_TOP_POSITION_FIELD));
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
        assert count == (COLS * ROWS);
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
