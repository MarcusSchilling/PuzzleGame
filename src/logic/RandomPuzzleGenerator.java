package logic;

import static picture.Constant.cols;
import static picture.Constant.rows;







import java.util.Random;

import javax.swing.JLabel;

import logic.Direction;
import picture.design.Pictures;
import util.Array;

public class RandomPuzzleGenerator implements PuzzleGeneratorService{
	
	private final Random random;
	private final JLabel[][] field;
	private int changes;

	
	public RandomPuzzleGenerator(JLabel[][] field){
		random = new Random();
		this.field = field;
	}
	
	public JLabel[][] createPuzzle(){
		do{
		changes++;
		if (random.nextBoolean()) {
			transposeShuffle();
		}else{
			shuffleRightLeft();
		}
		}while(random.nextInt(10*((rows+cols)/2))>1);
		return field;
	}
	
	private void transposeShuffle(){
		JLabel[][] label = Array.transpose(field);
		int timesChange = random.nextInt(rows);
		int row = random.nextInt(rows);
		
		if (random.nextBoolean()) {
			for (int i = 0; i < timesChange; i++) {
				ManipulateField.exchangeIcons(label[row], Direction.LEFT);
			}
		}else{
			for (int i = 0; i < timesChange; i++) {
				ManipulateField.exchangeIcons(label[row], Direction.RIGHT);
			}
		}
	}
	
	private void shuffleRightLeft(){
		int timesChange = random.nextInt(cols);
		int column = random.nextInt(cols);
		if (random.nextBoolean()) {
			for (int i = 0; i < timesChange; i++) {
				ManipulateField.exchangeIcons(field[column], Direction.UP);
			}
		}else{
			for (int i = 0; i < timesChange; i++) {
				ManipulateField.exchangeIcons(field[column], Direction.DOWN);
			}
		}
	}
	
	public int getChanges() {
		return changes;
	}
	
}
