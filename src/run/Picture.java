package run;

import image.ImagePath;
import image.ImageSplit;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.Canvas;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import picture.Playground;
import static picture.Constant.*;


public class Picture extends JFrame {
	
	private Playground playground;
	private String img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Picture frame = new Picture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Picture() throws Exception {
		img = "2";
		setUpWindow();
		playground = new Playground(this, img);
		playground.startGame();
	}
	
	private void setUpWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,widthWindow, heighWindow);
		getContentPane().setLayout(null);
		setTitle(gameTitle);
		try {
			setImageFavicon((Image)(ImageSplit.getScaledImage(ImageSplit.loadFullImage(img, false), 64, 64)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getUserChoosedFile(){
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		fileChooser.showOpenDialog(getParent());
		return fileChooser.getSelectedFile();
	}
	
	public void setImageFavicon(Image image){
		setIconImage(image);
	}

	public void reCreateGame(String path){
		setUpWindow();
		try {
			playground = new Playground(this, img);
			playground.startGame();
		} catch (Exception e) {
			
		}
	}
	
	
}