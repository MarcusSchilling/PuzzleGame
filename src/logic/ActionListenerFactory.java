package logic;

import static logic.Direction.*;
import static picture.design.Pictures.fields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;

import picture.Playground;
import picture.design.ButtonsHandler;
import util.Array;
public class ActionListenerFactory {

	private ActionListener actionListener;
	private Playground playground;
	
	
	public ActionListenerFactory(Playground play) {
		this.playground = play;
	}
	
	public static ActionListener  getActionListnerRestart(final Playground playground){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				playground.startGame();
			}
		};
	}

	public ActionListener getActionListener(Direction direction,  int s){
		if (direction == LEFT || direction == RIGHT) {
			rightOrLeftButton(s, direction);
			return actionListener;
		}else{
			bottomOrTopButton(s, direction);
			return actionListener;
		}
		
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	
	private void rightOrLeftButton(final int s, final Direction direction){
		this.actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(direction.toString());
				ManipulateField.exchangeIcons(fields[s], direction);
				playground.buttonExecution();
			}
		};
	}
	
	private void bottomOrTopButton(final int s, final Direction direction) {
		this.actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(direction.toString());
				ManipulateField.exchangeIcons(Array.transpose(fields)[s], direction);
				playground.buttonExecution();
			}
		};
	}
	
}
