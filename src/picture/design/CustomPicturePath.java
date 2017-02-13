package picture.design;

import image.ImagePath;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import picture.Playground;
import run.Picture;
import static picture.Constant.*;

public class CustomPicturePath {
	
	private JTextArea textArea;
	private Playground playground;
	private Picture picture;

	public CustomPicturePath(Playground playground, final Picture picture) {
		this.textArea = new JTextArea();
		this.playground = playground;
		this.picture = picture;
		textArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyText(e.getKeyCode()).equals(keyToOpenImagePathExplorer)){
					ImagePath.setImgPathGlobal(true);
					ImagePath.setImgPath(String.valueOf(picture.getUserChoosedFile()));
				}
			}
		});
	}
	
	public void drawTextArea() {
		textArea.setBounds(textAreaPositionAndSize());
		playground.drawToContentPane(textArea);
	}
	
	public File getPath() throws FileNotFoundException{
		String path = textArea.getText();
		path.replace("\\", "//");
		File file = new File(path);
		if(file.isFile() && !file.isHidden()){
			return file;
		}else{
			throw new FileNotFoundException("User Path doesn't exist or is hidden path: " + file.getAbsolutePath());
		}
	}
	
	private Rectangle textAreaPositionAndSize(){
		Rectangle rectangle = new Rectangle();
		rectangle.setSize(widthField, heightTextField);
		rectangle.setLocation(xMinPos(), yMinPos());
		return rectangle;
	}
	
	private int yMinPos(){
		int y = yMinPosMoveField + margin;
		return y;
	}
	
	private int xMinPos() {
		int x = xMinPosMoveField + widthMoveField + margin;
		return x;
	}
	
	public void enableTextArea(){
		textArea.setEditable(true);
	}
	
	public void diableTextArea(){
		textArea.setEditable(false);
	}
	
}
