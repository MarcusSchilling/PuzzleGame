package logic;

import image.IconExtension;
import util.Array;
import util.UtilizationService;

import javax.swing.*;

public class PictureGameService implements UtilizationService {

    private JLabel[][] fields;

    private int moves = 0;

    public PictureGameService(JLabel[][] field) {
        this.fields = field;
    }

    public void newAttempt() {
        moves++;
    }

    public int getMoves() {
        return moves;
    }

    @Override
    public boolean isCorrect() {
        JLabel[] labels = Array.twoDimensionIntoOne(fields);
        for (int i = 0; i < labels.length; i++) {
            if (((IconExtension) labels[i].getIcon()).getTargetPosition() != i) {
                return false;
            }
        }
        return true;
    }

    public void resetGameService() {
        this.moves = 0;
    }


}