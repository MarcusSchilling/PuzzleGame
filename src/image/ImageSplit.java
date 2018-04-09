package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static picture.Constant.*;

public class ImageSplit {


    // modified out of http://kalanir.blogspot.de/2010/02/how-to-split-image-into-chunks-java.html
    public static void split(String path, boolean isGlobalPath) throws IOException {
        File file;
        //1. Path
        //- global or local path
        if (isGlobalPath) {
            file = getFile(path);
        } else {
            file = getFileInProjectDir(path);
        }
        FileInputStream fis = new FileInputStream(file);

        //2. Magic each picture can be used
        BufferedImage image = getScaledImage(ImageIO.read(fis), WIDTH_FIELD, HEIGHT_FIELD); //reading the image file

        //3. customization for the specific project 
        int chunks = ROWS * COLS;
        int chunkWidth = image.getWidth() / COLS; // determines the chunk width and height
        int chunkHeight = image.getHeight() / ROWS;

        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks];
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        for (int i = 0; i < imgs.length; i++) {
            //4.modified by me
            String split = System.getProperty("file.separator");
            ImageIO.write(imgs[i], "jpg", new File(System.getProperty("user.dir") + split + "SplitedPictures" + split + "img" + i + ".jpg"));
        }
    }

    public static File getFileInProjectDir(String path) {
        String split = System.getProperty("file.separator");

        return new File(System.getProperty("user.dir") + split + "Picture" + split + path + ".jpg");
    }

    public static File getFile(String path) {
        return new File(path);
    }

    public static BufferedImage loadFullImage(String path, boolean isGlobalPath) throws IOException {
        BufferedImage imageIcon;
        if (isGlobalPath) {
            imageIcon = ImageIO.read(getFile(path));
        } else {
            imageIcon = (ImageIO.read(getFileInProjectDir(path)));
        }
        return imageIcon;
    }


    public static ImageIcon[] loadImages() throws IOException {
        String split = System.getProperty("file.separator");
        ImageIcon[] images = new ImageIcon[ROWS * COLS];
        for (int j = 0; j < (ROWS * COLS); j++) {
            images[j] = new IconExtension(ImageIO.read(new File(
                    System.getProperty("user.dir") + split + "SplitedPictures" + split + "img" + j + ".jpg")), j);
        }
        return images;
    }

    // Quelle : http://stackoverflow.com/questions/3967731/how-to-improve-the-performance-of-g-drawimage-method-for-resizing-images
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) width / imageWidth;
        double scaleY = (double) height / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(width, height, image.getType()));
    }

}