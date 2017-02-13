package logic;

import javax.swing.JLabel;

public interface PuzzleGeneratorService {

	public JLabel[][] createPuzzle();
	
	public int getChanges();
}
