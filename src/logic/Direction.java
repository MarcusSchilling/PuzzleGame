package logic;

import java.util.Random;

public enum Direction {

    RIGHT,
    LEFT,
    UP,
    DOWN;

    int colsOrRow;


    public Direction negate() {
        switch (this) {
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case LEFT:
                return RIGHT;
            case DOWN:
                return UP;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static Direction random() {
        Random random = new Random();
        return Direction.values()[random.nextInt(4)];
    }

    public void setColsOrRow(int colsOrRow) {
        this.colsOrRow = colsOrRow;
    }

    public int getColsOrRow() {
        return colsOrRow;
    }

    public boolean isHorizontal() {
        return this == RIGHT || this == LEFT;
    }
}