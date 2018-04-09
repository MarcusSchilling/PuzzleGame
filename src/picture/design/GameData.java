package picture.design;

import logic.PictureGameService;
import picture.Playground;

import javax.swing.*;
import java.awt.*;

import static picture.Constant.*;

public class GameData {

    private JLabel moves;
    private Playground playground;
    private PictureGameService gameService;

    public GameData(Playground playground, PictureGameService gameService) {
        this.playground = playground;
        this.gameService = gameService;
    }

    public void drawMoveField() {
        this.moves = new JLabel("");
        moves.setBounds(moves());
        playground.drawToContentPane(moves);
    }

    public void actualizeMovesFieldText() {
        moves.setText(String.valueOf(gameService.getMoves()));
    }


    private Rectangle moves() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(WIDTH_MOVE_FIELD, HEIGHT_MOVE_FIELD);
        rectangle.setLocation(X_MIN_POS_MOVE_FIELD, Y_MIN_POS_MOVE_FIELD);
        return rectangle;
    }

}
