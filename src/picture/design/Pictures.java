package picture.design;

import static picture.Constant.cols;
import static picture.Constant.heightField;
import static picture.Constant.maxLeftPositionField;
import static picture.Constant.maxTopPositionField;
import static picture.Constant.minLeftPositionField;
import static picture.Constant.minTopPositionField;
import static picture.Constant.rows;
import static picture.Constant.widthField;
import static picture.design.Pictures.fields;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import picture.Playground;

public class Pictures {

	public final static JLabel[][] fields = new JLabel[rows][cols];
	private Playground playground;

	public Pictures(Playground playground){
		this.playground = playground;
	}
	
	public void addPlayingField(){
		int horizontalPosition = 0;
		int verticalPosition = 0;
		for (JLabel[] jLabels : fields) {
			for (JLabel jLabel : jLabels) {
				jLabel = new JLabel("");
				jLabel.setBounds(positionFields(horizontalPosition, verticalPosition));
				fields[verticalPosition][horizontalPosition] = jLabel;
				horizontalPosition++;
			}
			horizontalPosition = 0;
			verticalPosition ++;
		}
		
		playground.drawToContentPane(fields);
	}
	
	private Rectangle positionFields(double horizontalPosition, double verticalPosition){
		Rectangle rec = new Rectangle((int)x(horizontalPosition),(int)y(verticalPosition), 
				(widthField/cols), (heightField/rows));
		return rec;
	}
	
	private double x(double horizontalPosition){
		double x;
		if (horizontalPosition == 0) {
			x = minLeftPositionField;
		}else{
			x = minLeftPositionField + 
					((double)(horizontalPosition) / (cols)) * 
					((maxLeftPositionField ) - minLeftPositionField);
		}
		return x;
	}
	
	private double y(double verticalPosition){
		double y;
		if (verticalPosition == 0) {
			y = minTopPositionField;
		}else{
			y = minTopPositionField + 
					(((double)((verticalPosition) / (rows))) * ((maxTopPositionField) - minTopPositionField));	
		}
		return y;
	}
	
	public void actualizeImages(List<ImageIcon> images){
		int count = 0;
		for (JLabel[] arrayLabel : fields) {
			for (JLabel jLabel : arrayLabel) {
				jLabel.setIcon(images.get(count));
				count++;
			}
		}
		assert count == (cols*rows);
	}
	
	public void makeFieldsInvisible(){
		for (JLabel[] jLabels : fields) {
			for (JLabel jLabel : jLabels) {
				jLabel.setVisible(false);
			}
		}
	}
	
	public void makeFieldsVisible(){
		for (JLabel[] jLabels : fields) {
			for (JLabel jLabel : jLabels) {
				jLabel.setVisible(true);
			}
		}
	}
	
}
