package picture.design;

import image.ImagePath;
import image.ImageSplit;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import logic.PictureGameService;
import picture.Playground;
import static picture.Constant.*;

public class ResultDesign {
	
	private JLabel resultLabel;
	private JLabel pictureLabel;
	private Playground playground;
	private PictureGameService gameService;

	public ResultDesign(Playground playground, PictureGameService gameService) {
		this.resultLabel = new JLabel();
		this.playground = playground;		
		this.gameService = gameService;
		this.pictureLabel = new JLabel();
	}
	
	public void drawLabels(){
		resultLabel.setBounds(getPositionAndSizeTextField());
		resultLabel.setBackground(result);
		playground.drawToContentPane(resultLabel);
		pictureLabel.setBounds(getPositionAndSizePictureField());
		playground.drawToContentPane(pictureLabel);
		fillPicture();
	}
	
	public void fillLabelsWithGameResult(){
		resultLabel.setText(resultContent());
		fillPicture();
	}
	
	private void fillPicture(){
		try {
			BufferedImage image = ImageSplit.loadFullImage(ImagePath.getImgPath(), ImagePath.isGlobal());
			int width = getPositionAndSizePictureField().width;
			int height = getPositionAndSizePictureField().height;
			BufferedImage scaledImage = ImageSplit.getScaledImage(image, width , height);
			pictureLabel.setIcon(new ImageIcon(scaledImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private Rectangle getPositionAndSizeTextField(){
		Rectangle rectangle = new Rectangle();
		rectangle.setSize(widthField, heightField);
		rectangle.setLocation(minLeftPositionField, minTopPositionField);
		return rectangle;
	}
	
	private Rectangle getPositionAndSizePictureField(){
		Rectangle rectangle = new Rectangle();
		int x = (int) (minLeftPositionField + (double)(widthField/2) - margin);
		int y = (int) (minTopPositionField + (double) (heightField*(1.0/4.0)));
		rectangle.setLocation(x, y);
		rectangle.setSize(widthField/2, heightField/2);
		return rectangle;
	}
	
	private String resultContent(){
		if (1 == gameService.getMoves()) {
			return youWonInOneMove;
		}else{
			return String.format(youWonInMoves, gameService.getMoves());
		}
	}
	
	public void makeResultDesignsInvisible() {
		resultLabel.setVisible(false);
		pictureLabel.setVisible(false);
	}
	
	public void makeResultDesignsVisible(){
		resultLabel.setVisible(true);
		pictureLabel.setVisible(true);
	}

}
