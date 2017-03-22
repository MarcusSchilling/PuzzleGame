package logic;

import picture.Constant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleSolver {


    private final JLabel[][] field;
    private final PictureGameService gameService;
    private final List<Integer> list;
    private int best;

    public PuzzleSolver(final JLabel[][] field, PuzzleGeneratorService puzzleGenerator) {
        this.field = field;
        this.gameService = new PictureGameService(getImages(), field);
        best = puzzleGenerator.getChanges();
        this.list = new ArrayList<Integer>();
    }

    private List<ImageIcon> getImages() {
        List<ImageIcon> images = new ArrayList<ImageIcon>();
        for (JLabel[] jLabels : field) {
            for (JLabel jLabel : jLabels) {
                images.add((ImageIcon) jLabel.getIcon());
            }
        }
        return images;
    }


    public int solve() {
        best++;
        solve(field, 0, best);

        for (int change : list) {
            if (best > change) {
                this.best = change;
            }
        }

        return best;
    }

    public boolean solve(JLabel[][] field, int currentChanges, int maxChanges) {
        if (gameService.isCorrect()) {
            if (currentChanges < maxChanges) {
                System.out.println("solution: " + currentChanges);
                addSolution(currentChanges);
            }
            return true;
        }

        if (currentChanges < maxChanges) {
            for (int column = 0; column < Constant.cols; column++) {
                ManipulateField.exchangeIconsVirtual(field, column, Direction.LEFT);
                if (currentChanges + 1 < maxChanges && solve(field, currentChanges + 1, maxChanges)) {
                    if (currentChanges + 1 < maxChanges) {
                        maxChanges = currentChanges + 1;
                        return true;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    ManipulateField.exchangeIconsVirtual(field, column, Direction.RIGHT);
                }

                if (currentChanges + 1 < maxChanges && solve(field, currentChanges + 1, maxChanges)) {
                    if (currentChanges + 1 < maxChanges) {
                        maxChanges = currentChanges + 1;
                        return true;
                    }
                }
                ManipulateField.exchangeIconsVirtual(field, column, Direction.LEFT);

            }
            for (int row = 0; row < Constant.rows; row++) {
                ManipulateField.exchangeIconsVirtual(field, row, Direction.UP);
                if (currentChanges + 1 < maxChanges && solve(field, currentChanges + 1, maxChanges)) {
                    if (currentChanges + 1 < maxChanges) {
                        maxChanges = currentChanges + 1;
                        return true;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    ManipulateField.exchangeIconsVirtual(field, row, Direction.DOWN);
                }
                if (currentChanges + 1 < maxChanges && solve(field, currentChanges + 1, maxChanges)) {
                    if (currentChanges + 1 < maxChanges)
                        return true;

                }
                ManipulateField.exchangeIconsVirtual(field, row, Direction.UP);
            }
        }
        return false;
    }


    private void addSolution(int moves) {
        list.add(moves);
    }

}
