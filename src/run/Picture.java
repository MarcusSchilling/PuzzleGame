package run;

import picture.Playground;
import util.OpenDialogClosed;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static picture.Constant.*;


public class Picture extends JFrame {

    private Playground playground;

    /**
     * Create the frame.
     *
     * @throws Exception
     */
    public Picture() throws Exception {
        setUpWindow();
        playground = new Playground(this);
        playground.startGame();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Picture frame = new Picture();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setUpWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, WIDTH_WINDOW, HEIGHT_WINDOW);
        getContentPane().setLayout(null);
        setTitle(GAME_TITLE);
    }

    public File getUserChoosedFile() {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
        if(fileChooser.showOpenDialog(getParent()) == JFileChooser.CANCEL_OPTION) {
            throw new OpenDialogClosed();
        }
        return fileChooser.getSelectedFile();
    }

    public void setImageFavicon(Image image) {
        setIconImage(image);
    }

}