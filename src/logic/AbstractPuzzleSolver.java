package logic;

import java.util.List;

/**
 * Created by schilling on 28.06.17.
 */
public class AbstractPuzzleSolver {

    private int[][] field;


    public AbstractPuzzleSolver(int[][] field) {
        this.field = field;
    }


    private boolean isCorrect() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] != (i * j)) {
                    return false;
                }
            }
        }
        return true;
    }


    public void solve(int[][] field, List<Direction> directions, int best) {
        int currentSize = directions.size();
        if (currentSize >= best) {
            return;
        }
        if (isCorrect()) {
            best = currentSize;
            return;
        }
        for (Direction direction : Direction.values()) {
            int border;
            if (direction == Direction.DOWN || direction == Direction.UP) {
                border = field.length;
            } else {
                border = field[0].length;
            }
            for (int i = 0; i < border; i++) {
                /**last = direction;
                JLabel[][] jLabels = ManipulateField.exchangeIconsVirtual(field, i, direction);
                lastI = i;
                List<Direction> collect = new ArrayList<>(directions);
                collect.add(direction);
                solve(jLabels, collect);*/
            }
        }
    }
}
