package picture.design;

import java.awt.Rectangle;

import javax.swing.JLabel;
import static picture.Constant.*;

public class PuzzleSolverTest {
	
	private final JLabel label;

	public PuzzleSolverTest() {
		this.label = new JLabel();
		label.setBounds(getPositionAndSize());
		
	}
	
	private Rectangle getPositionAndSize(){
		Rectangle rectangle = new Rectangle(getX(),getY(), width() , height());
		return rectangle;
	}
	
	private int getX(){
		return (minLeftButtonPosition+widthField+(buttonHeight*2) + margin*2);
	}
	
	private int getY(){
		return (margin*2);
	}
	
	private int width(){
		return widthMinMovesLabel;
	}
	
	private int height(){
		return heightMinMovesLabel;
	}

	public void setMinMovesToSolve(int minMovesToSolve){
		label.setText(String.valueOf(minMovesToSolve));
	}
	
}
