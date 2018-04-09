package picture.design;

import image.ImagePath;
import image.ImageSplit;
import logic.PictureGameService;
import picture.Playground;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static picture.Constant.*;

public class ResultDesign {

    private JLabel resultLabel;
    private JLabel pictureLabel;
    private Playground playground;
    private PictureGameService gameService;
    private int minMoves;

    public ResultDesign(Playground playground, PictureGameService gameService, int minMoves) {
        this.resultLabel = new JLabel();
        this.playground = playground;
        this.gameService = gameService;
        this.pictureLabel = new JLabel();
        this.minMoves = minMoves;
    }

    public void drawLabels() {
        resultLabel.setBounds(getPositionAndSizeTextField());
        resultLabel.setBackground(RESULT);
        playground.drawToContentPane(resultLabel);
        pictureLabel.setBounds(getPositionAndSizePictureField());
        playground.drawToContentPane(pictureLabel);
        fillPicture();
    }

    public void fillLabelsWithGameResult() {
        resultLabel.setText("");
        resultLabel.setText(resultContent());
        fillPicture();
    }

    private void fillPicture() {
        try {
            BufferedImage image = ImageSplit.loadFullImage(ImagePath.getImgPath(), ImagePath.isGlobal());
            int width = getPositionAndSizePictureField().width;
            int height = getPositionAndSizePictureField().height;
            BufferedImage scaledImage = ImageSplit.getScaledImage(image, width, height);
            pictureLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Rectangle getPositionAndSizeTextField() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(WIDTH_FIELD, HEIGHT_FIELD);
        rectangle.setLocation(MIN_LEFT_POSITION_FIELD, MIN_TOP_POSITION_FIELD);
        return rectangle;
    }

    private Rectangle getPositionAndSizePictureField() {
        Rectangle rectangle = new Rectangle();
        int x = (int) (MIN_LEFT_POSITION_FIELD + (double) (WIDTH_FIELD / 2) - MARGIN);
        int y = (int) (MIN_TOP_POSITION_FIELD + (double) (HEIGHT_FIELD * (1.0 / 4.0)));
        rectangle.setLocation(x, y);
        rectangle.setSize(WIDTH_FIELD / 2, HEIGHT_FIELD / 2);
        return rectangle;
    }

    private String resultContent() {
        assert gameService.getMoves() >= minMoves;
        if (gameService.getMoves() == minMoves) {
            return YOU_WON_IN_MIN_POSSIBLE_MOVES;
        } else if (1 == gameService.getMoves()) {
            return YOU_WON_IN_ONE_MOVE;
        } else {
            return String.format(YOU_WON_IN_MOVES, gameService.getMoves());
        }
    }

    public void makeResultDesignsInvisible() {
        resultLabel.setVisible(false);
        pictureLabel.setVisible(false);
    }

    public void makeResultDesignsVisible() {
        resultLabel.setVisible(true);
        pictureLabel.setVisible(true);
    }

}
