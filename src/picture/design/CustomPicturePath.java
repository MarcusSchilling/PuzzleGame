package picture.design;

import image.ImagePath;
import picture.Playground;
import run.Picture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;

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
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.getKeyText(e.getKeyCode()).equals(KEY_TO_OPEN_IMAGE_PATH_EXPLORER) && !playground.inGame()) {
                    openExplorerForPicturePath();
                }
            }
        });
    }

    public void openExplorerForPicturePath() {
        ImagePath.setImgPathGlobal(true);
        ImagePath.setImgPath(String.valueOf(picture.getUserChoosedFile()));
    }

    public void drawTextArea() {
        textArea.setBounds(textAreaPositionAndSize());
        playground.drawToContentPane(textArea);
    }

    public File getPath() throws FileNotFoundException {
        String path = textArea.getText();
        path = path.replace("\\", "//");
        File file = new File(path);
        if (file.isFile() && !file.isHidden()) {
            return file;
        } else {
            throw new FileNotFoundException("User Path doesn't exist or is hidden path: " + file.getAbsolutePath());
        }
    }

    private Rectangle textAreaPositionAndSize() {
        Rectangle rectangle = new Rectangle();
        rectangle.setSize(WIDTH_FIELD, HEIGHT_TEXT_FIELD);
        rectangle.setLocation(xMinPos(), yMinPos());
        return rectangle;
    }

    private int yMinPos() {
        return Y_MIN_POS_MOVE_FIELD + MARGIN;
    }

    private int xMinPos() {
        return X_MIN_POS_MOVE_FIELD + WIDTH_MOVE_FIELD + MARGIN;
    }

    public void enableTextArea() {
        textArea.setEditable(true);
    }

    public void disableTextArea() {
        textArea.setEditable(false);
    }

}
