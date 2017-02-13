package picture;

import java.awt.Color;
public class Constant {
	
	
	public static final int rows = 6;
	public static final int cols = 6;

	
	//general
	public static final int margin = 10;
	public static final int widthWindow = 800;
	public static final int heighWindow = 800;
	public static final Color result = new Color(51, 25, 0, 100);
	public static final String gameTitle = "Puzzle Game";
	public static final String keyToOpenImagePathExplorer = "F1";
	

	//puzzle generator
	//from one to 9 the lower the degree the more difficult the game gets
	public static final int degreeOfDifficulty = 0;

	
	//moves field
	public static final int widthMoveField = 50;
	public static final int heighMoveField = 50;
	public static final int xMinPosMoveField = 10;
	public static final int yMinPosMoveField = 10;
	
	//Field
	public static final int heightField = 500;
	public static final int widthField = 500;
	public static final int minTopPositionField = 112;
	public static final int minLeftPositionField = 106;
	public static final int maxTopPositionField =  minTopPositionField + ((heightField/cols)*(cols));
	public static final int maxLeftPositionField = minLeftPositionField +((widthField/rows)*(rows));
	
	//TextField path
	public static final int heightTextField = 30;
	public static final int widthTextField = 400;
	
	//Button
	public static final int buttonHeight = 50;
	public static final int minTopButtonPosition = 73;
	public static final int minLeftButtonPosition = (minLeftPositionField-(margin+buttonHeight));

	public static final String buttonTextUp = "up";
	public static final String buttonTextDown = "down";
	public static final String buttonTextLeft = "<";
	public static final String buttonTextRight = ">";
	
	//restart button
	public static final int widthRestartButton = 200;
	public static final int heighRestartButton = 40;
	public static final String restartButtonText = "Restart";
	
	
	public static final String youWonInMoves = "Du hast gewonnen in %d Spielz�gen";
	public static final String youWonInOneMove= "Du hast gewonnen in einem Spielzug";
	
	
	// label min Moves test show
	public static final int widthMinMovesLabel = 50;
	public static final int heightMinMovesLabel = 50;
}
