package picture.design;

import logic.ActionListenerFactory;
import picture.Playground;

import javax.swing.*;
import java.awt.*;

import static picture.Constant.*;

public class RestartButton {

    private JButton button;
    private Playground playground;

    public RestartButton(Playground playground) {
        this.button = new JButton();
        this.playground = playground;
        functionButton();
    }

    public void drawButton() {
        button.setBounds(getPositionAndSize());
        button.setText(RESTART_BUTTON_TEXT);
        playground.drawToContentPane(button);
    }

    public void turnButtonOff() {
        button.setEnabled(false);
    }

    public void turnButtonOn() {
        button.setEnabled(true);
    }

    private void functionButton() {
        button.addActionListener(ActionListenerFactory.getActionListnerRestart(playground));
    }

    private Rectangle getPositionAndSize() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(WIDTH_RESTART_BUTTON, HEIGHT_RESTART_BUTTON);
        rectangle.setLocation(WIDTH_WINDOW - (MARGIN * 2 + WIDTH_RESTART_BUTTON), (MARGIN));
        return rectangle;
    }

}
