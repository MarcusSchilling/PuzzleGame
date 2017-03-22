package run;

import image.ImageSplit;
import picture.Playground;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static picture.Constant.*;


public class Picture extends JFrame {

    private Playground playground;
    private String img;

    /**
     * Create the frame.
     *
     * @throws Exception
     */
    public Picture() throws Exception {
        img = "2";
        setUpWindow();
        playground = new Playground(this, img);
        playground.startGame();
    }

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

    private void setUpWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, widthWindow, heighWindow);
        getContentPane().setLayout(null);
        setTitle(gameTitle);
        try {
            setImageFavicon((Image) (ImageSplit.getScaledImage(ImageSplit.loadFullImage(img, false), 64, 64)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getUserChoosedFile() {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
        fileChooser.showOpenDialog(getParent());
        return fileChooser.getSelectedFile();
    }

    public void setImageFavicon(Image image) {
        setIconImage(image);
    }

    public void reCreateGame(String path) {
        setUpWindow();
        try {
            playground = new Playground(this, img);
            playground.startGame();
        } catch (Exception e) {

        }
    }


}