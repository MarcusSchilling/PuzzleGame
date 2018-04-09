package logic;

import picture.design.Pictures;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzleSolver {


    private final JLabel[][] field;
    private final PictureGameService gameService;
    private int best;
    private final PuzzleGeneratorService puzzleGeneratorService;
    private List<Direction> minPath;



    public PuzzleSolver(final JLabel[][] field, PuzzleGeneratorService puzzleGenerator) throws RuntimeException {
        this.field = field;
        this.gameService = new PictureGameService(Pictures.fields);
        this.best = puzzleGenerator.getChanges();
        this.puzzleGeneratorService = puzzleGenerator;
    }

    public int solve() {
        best = puzzleGeneratorService.getChanges();
        if (gameService.isCorrect()) {
            return 0;
        }
        ArrayList<Direction> directions = new ArrayList<>();
        if (best < 2) {
            return best;
        }
        solve(field, directions);
        return best;
    }

    public void solve(JLabel[][] field, List<Direction> directions) {
        int currentSize = directions.size();
        if (currentSize >= best) {
            return;
        }
        if (gameService.isCorrect()) {
            best = currentSize;
            Optional<String> reduce = directions.stream()
                    .map(Enum::toString)
                    .reduce((s, s2) -> s + " " + s2);
            System.out.println(reduce.toString());
            minPath = directions;
            System.out.println(reduce);
            return;
        }
        if (currentSize >= best - 1) {
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
                JLabel[][] jLabels = ManipulateField.exchangeIconsVirtual(field, i, direction);
                direction.setColsOrRow(i);
                List<Direction> collect = new ArrayList<>(directions);
                collect.add(direction);
                solve(jLabels, collect);
            }
        }
    }
}
