package picture.design;

import logic.PictureGameService;
import picture.Playground;
import util.Array;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static logic.Direction.*;
import static picture.Constant.cols;
import static picture.Constant.rows;

public class ButtonsHandler {

    private List<JButton> buttons = new ArrayList<JButton>(cols);
    private Playground playground;
    private PictureGameService gameService;
    private GameData gameData;
    private Random random = new Random();
    private ButtonField buttonField;
    private JLabel[][] fields;

    public ButtonsHandler(Playground playground, PictureGameService gameService, GameData gameData) {
        this.playground = playground;
        this.gameService = gameService;
        this.gameData = gameData;
        this.buttonField = new ButtonField(this, playground);
        fields = picture.design.Pictures.fields;
    }

    public void turnButtonsOn() {
        for (JButton but : buttons) {
            but.setEnabled(true);
        }
    }

    public void turnButtonsOff() {
        for (JButton but : buttons) {
            but.setEnabled(false);
        }
    }

    public void addButtons() {
        buttonsUpAndDown();
        buttonsLeftAndRight();
    }

    private void buttonsUpAndDown() {
        JButton button;
        for (int i = 0; i < cols; i++) {
            button = buttonField.buttonsUpOrDown(DOWN, i);
            buttons.add(button);
        }
        for (int i = 0; i < cols; i++) {
            button = buttonField.buttonsUpOrDown(UP, i);
            buttons.add(button);
        }
        playground.drawToContentPane(Array.listToArray(buttons));
    }

    private void buttonsLeftAndRight() {
        JButton button;
        for (int i = 0; i < rows; i++) {
            button = buttonField.buttonsLeftOrRight(RIGHT, i);
            buttons.add(button);
        }
        for (int i = 0; i < fields.length; i++) {
            button = buttonField.buttonsLeftOrRight(LEFT, i);
            buttons.add(button);
        }
        playground.drawToContentPane(Array.listToArray(buttons));
    }

}

