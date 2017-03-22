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
        button.setText(restartButtonText);
        playground.drawToContentPane(button);
    }

    public void turnButtonOff() {
        button.setEnabled(true);
    }

    public void turnButtonOn() {
        button.setEnabled(true);
    }

    private void functionButton() {
        button.addActionListener(ActionListenerFactory.getActionListnerRestart(playground));
    }

    private Rectangle getPositionAndSize() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(widthRestartButton, heightRestartButton);
        rectangle.setLocation(widthWindow - (margin * 2 + widthRestartButton), (margin));
        return rectangle;
    }

}
