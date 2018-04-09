package picture.design;

import logic.ActionListenerFactory;
import logic.Direction;
import picture.Playground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static logic.Direction.*;
import static picture.Constant.*;

public class ButtonField {


    private ActionListenerFactory actionListenerFactory;

    public ButtonField(Playground playground) {
        actionListenerFactory = new ActionListenerFactory(playground);
    }

    public JButton buttonsUpOrDown(Direction direction, final int s) {
        if (direction == LEFT || direction == RIGHT) {
            throw new IllegalArgumentException();
        }
        String buttonText = (direction == UP) ? BUTTON_TEXT_UP : BUTTON_TEXT_DOWN;
        Rectangle positionAndSize = (direction == UP) ? positionButtonUp(s) : positionButtonDown(s);
        ActionListener actionListener = actionListenerFactory.getActionListener(direction, s);
        JButton button = new JButton(buttonText);
        button.setBounds(positionAndSize);
        button.addActionListener(actionListener);
        return button;
    }

    public JButton buttonsLeftOrRight(Direction direction, final int s) {
        if (direction == UP || direction == DOWN) {
            throw new IllegalArgumentException();
        }
        String buttonText = (direction == LEFT) ? BUTTON_TEXT_LEFT : BUTTON_TEXT_RIGHT;
        JButton button = new JButton(buttonText);
        Rectangle positionAndSize = (direction == LEFT) ? invert(positionButtonUp(s)) : invert(positionButtonDown(s));
        positionAndSize.setSize((int) positionAndSize.getSize().getWidth(), calculateHeightRightLeftField());
        positionAndSize.x = (direction == LEFT) ? MIN_LEFT_BUTTON_POSITION : (MIN_LEFT_BUTTON_POSITION + WIDTH_FIELD + BUTTON_HEIGHT + (2 * MARGIN));
        positionAndSize.y = calculateYRightLeft(s);
        button.setBounds(positionAndSize);
        button.addActionListener(actionListenerFactory.getActionListener(direction, s));
        return button;
    }

    private Rectangle positionButtonUp(int i) {
        Rectangle rec = new Rectangle();
        rec.setSize((WIDTH_FIELD / COLS), BUTTON_HEIGHT);
        int x = MIN_LEFT_POSITION_FIELD + (((WIDTH_FIELD) / COLS) * i);
        rec.setLocation(x, MIN_TOP_BUTTON_POSITION - 20);
        return rec;
    }

    private Rectangle positionButtonDown(int i) {
        Rectangle rec = new Rectangle();
        rec.setSize((WIDTH_FIELD / COLS), BUTTON_HEIGHT);
        int x = MIN_LEFT_POSITION_FIELD + (((WIDTH_FIELD) / COLS) * i);
        rec.setLocation(x, MIN_TOP_BUTTON_POSITION + HEIGHT_FIELD + BUTTON_HEIGHT + MARGIN);
        return rec;
    }

    private int calculateHeightRightLeftField() {
        return (int) ((double) HEIGHT_FIELD / (double) ROWS);
    }

    private int calculateYRightLeft(int i) {
        return (int) (MIN_TOP_POSITION_FIELD + ((double) i / ROWS) * (double) HEIGHT_FIELD);
    }

    private Rectangle invert(Rectangle rectangle) {
        Rectangle back = new Rectangle();
        back.setBounds(rectangle.y, rectangle.x, rectangle.height, rectangle.width);
        return back;
    }
}
