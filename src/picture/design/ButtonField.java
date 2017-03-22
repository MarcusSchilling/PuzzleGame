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

    public ButtonField(ButtonsHandler buttonsHandler, Playground playground) {
        actionListenerFactory = new ActionListenerFactory(playground);
    }

    public JButton buttonsUpOrDown(Direction direction, final int s) {
        if (direction == LEFT || direction == RIGHT) {
            throw new IllegalArgumentException();
        }
        String buttonText = (direction == UP) ? buttonTextUp : buttonTextDown;
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
        String buttonText = (direction == LEFT) ? buttonTextLeft : buttonTextRight;
        JButton button = new JButton(buttonText);
        Rectangle positionAndSize = (direction == LEFT) ? invert(positionButtonUp(s)) : invert(positionButtonDown(s));
        positionAndSize.setSize((int) positionAndSize.getSize().getWidth(), calculateHeightRightLeftField());
        positionAndSize.x = (direction == LEFT) ? minLeftButtonPosition : (minLeftButtonPosition + widthField + buttonHeight + (2 * margin));
        positionAndSize.y = calculateYRightLeft(s);
        button.setBounds(positionAndSize);
        button.addActionListener(actionListenerFactory.getActionListener(direction, s));
        return button;
    }

    private Rectangle positionButtonUp(int i) {
        Rectangle rec = new Rectangle();
        rec.setSize((widthField / cols), buttonHeight);
        int x = minLeftPositionField + (((widthField) / cols) * i);
        rec.setLocation(x, minTopButtonPosition - 20);
        return rec;
    }

    private Rectangle positionButtonDown(int i) {
        Rectangle rec = new Rectangle();
        rec.setSize((widthField / cols), buttonHeight);
        int x = minLeftPositionField + (((widthField) / cols) * i);
        rec.setLocation(x, minTopButtonPosition + heightField + buttonHeight + margin);
        return rec;
    }

    private int calculateHeightRightLeftField() {
        return (int) ((double) heightField / (double) rows);
    }

    private int calculateYRightLeft(int i) {
        return (int) (minTopPositionField + ((double) i / rows) * (double) heightField);
    }

    private Rectangle invert(Rectangle rectangle) {
        Rectangle back = new Rectangle();
        back.setBounds(rectangle.y, rectangle.x, rectangle.height, rectangle.width);
        return back;
    }
}
