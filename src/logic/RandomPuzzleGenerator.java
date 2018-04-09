package logic;

import util.Array;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static picture.Constant.*;

public class RandomPuzzleGenerator implements PuzzleGeneratorService {

    private final Random random;
    private final JLabel[][] field;
    private int changes;
    private List<Direction> generatedPath = new ArrayList<>();


    public RandomPuzzleGenerator(JLabel[][] field) {
        this.random = new Random();
        this.field = field;
    }

    public void createPuzzle() {
        changes = DEGREE_OF_DIFFICULTY;
        for (int i = 0; i < changes; i++) {
            if (random.nextBoolean()) {
                transposeShuffle();
            } else {
                shuffleRightLeft();
            }
        }
    }

    private void transposeShuffle() {
        JLabel[][] label = Array.transpose(field);
        int row = random.nextInt(ROWS);

        if (random.nextBoolean()) {
            ManipulateField.exchangeIcons(label[row], Direction.LEFT);
            Direction direction = Direction.LEFT;
            direction.setColsOrRow(row);
            generatedPath.add(direction);
        } else {
            ManipulateField.exchangeIcons(label[row], Direction.RIGHT);
            Direction direction = Direction.RIGHT;
            direction.setColsOrRow(row);
            generatedPath.add(direction);

        }
    }

    private void shuffleRightLeft() {
        int column = random.nextInt(COLS);
        if (random.nextBoolean()) {
            ManipulateField.exchangeIcons(field[column], Direction.UP);
            Direction direction = Direction.UP;
            direction.setColsOrRow(column);
            generatedPath.add(direction);
        } else {
            ManipulateField.exchangeIcons(field[column], Direction.DOWN);
            Direction direction = Direction.DOWN;
            direction.setColsOrRow(column);
            generatedPath.add(direction);
        }
    }

    public int getChanges() {
        return changes;
    }

    public List<Direction> getGeneratedPath() {
        return generatedPath;
    }
}
