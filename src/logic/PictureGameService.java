package logic;

import image.IconExtension;
import util.Array;
import util.UtilizationService;

import javax.swing.*;
import java.util.List;

public class PictureGameService implements UtilizationService {

    private List<ImageIcon> imagesCorrect;
    private JLabel[][] fields;

    private int moves = 0;

    public PictureGameService(List<ImageIcon> imagesCorrect) {
        this.imagesCorrect = imagesCorrect;
        this.fields = picture.design.Pictures.fields;
    }

    public PictureGameService(List<ImageIcon> imagesCorrect, JLabel[][] field) {
        this.imagesCorrect = imagesCorrect;
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
        int count = 0;
        for (JLabel jLabels : Array.twoDimensionIntoOne(fields)) {
            if (!compareIcon(jLabels.getIcon(), (Icon) imagesCorrect.get(count))) {
                return false;
            }
            count++;
        }
        return true;
    }

    private boolean compareIcon(Icon first, Icon second) {
        IconExtension firstIm = (IconExtension) first;
        IconExtension secondIm = (IconExtension) second;
        return firstIm.getTargetPosition() == secondIm.getTargetPosition();
    }

    public void resetGameService() {
        this.moves = 0;
    }


    public void setImagesCorrect(List<ImageIcon> imagesCorrect) {
        this.imagesCorrect = imagesCorrect;
    }
}
