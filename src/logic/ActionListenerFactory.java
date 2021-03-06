package logic;

import picture.Playground;
import util.Array;

import java.awt.event.ActionListener;

import static logic.Direction.LEFT;
import static logic.Direction.RIGHT;
import static picture.design.Pictures.fields;

public class ActionListenerFactory {

    private ActionListener actionListener;
    private Playground playground;


    public ActionListenerFactory(Playground play) {
        this.playground = play;
    }

    public static ActionListener getActionListnerRestart(final Playground playground) {
        return e -> playground.startGame();
    }

    public ActionListener getActionListener(Direction direction, int s) {
        if (direction == LEFT || direction == RIGHT) {
            rightOrLeftButton(s, direction);
            return actionListener;
        } else {
            bottomOrTopButton(s, direction);
            return actionListener;
        }

    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    private void rightOrLeftButton(final int s, final Direction direction) {
        this.actionListener = e -> {
            System.out.println(direction.toString());
            ManipulateField.exchangeIcons(fields[s], direction);
            playground.buttonExecution();
        };
    }

    private void bottomOrTopButton(final int s, final Direction direction) {
        this.actionListener = e -> {
            System.out.println(direction.toString());
            ManipulateField.exchangeIcons(Array.transpose(fields)[s], direction);
            playground.buttonExecution();
        };
    }

}
