package picture.design;

import picture.Playground;
import util.Array;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static logic.Direction.*;
import static picture.Constant.COLS;
import static picture.Constant.ROWS;

public class ButtonsHandler {

    private List<JButton> buttons = new ArrayList<JButton>(COLS);
    private Playground playground;
    private ButtonField buttonField;
    private JLabel[][] fields;

    public ButtonsHandler(Playground playground) {
        this.playground = playground;
        this.buttonField = new ButtonField(playground);
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
        for (int i = 0; i < COLS; i++) {
            button = buttonField.buttonsUpOrDown(DOWN, i);
            buttons.add(button);
        }
        for (int i = 0; i < COLS; i++) {
            button = buttonField.buttonsUpOrDown(UP, i);
            buttons.add(button);
        }
        playground.drawToContentPane(Array.listToArray(buttons));
    }

    private void buttonsLeftAndRight() {
        JButton button;
        for (int i = 0; i < ROWS; i++) {
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

