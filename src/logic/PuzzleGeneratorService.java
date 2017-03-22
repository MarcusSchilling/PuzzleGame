package logic;

import javax.swing.*;

public interface PuzzleGeneratorService {

    public JLabel[][] createPuzzle();

    public int getChanges();
}
