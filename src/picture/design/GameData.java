package picture.design;

import java.awt.Rectangle;

import javax.swing.JLabel;

import logic.PictureGameService;
import picture.Playground;
import static picture.Constant.*;

public class GameData {
	
	private JLabel moves;
	private Playground playground;
	private PictureGameService gameService;

	public GameData(Playground playground, PictureGameService gameService) {
		this.playground = playground;
		this.gameService = gameService;
	}
	
	public void drawMoveField(){
		this.moves = new JLabel("");
		moves.setBounds(moves());
		playground.drawToContentPane(moves);
	}

	public void actualizeMovesFieldText(){
		moves.setText(String.valueOf(gameService.getMoves()));
	}

	
	private Rectangle moves(){
		Rectangle rectangle = new Rectangle();
		rectangle.setSize(widthMoveField, heighMoveField);
		rectangle.setLocation(xMinPosMoveField, yMinPosMoveField);
		return rectangle;
	}
	
}
