package image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import static picture.Constant.*;

public class ImageSplit {

	
	// modified out of http://kalanir.blogspot.de/2010/02/how-to-split-image-into-chunks-java.html
    public static void split(String path, boolean isGlobalPath) throws IOException {
    	File file;
    	//1. Path 
    	//- global or local path
    	if (isGlobalPath) {
    		file = getFile(path);
		}else{
			file = getFileInProjectDir(path);
		}
        FileInputStream fis = new FileInputStream(file);
        
        //2. Magic each picture can be used
        BufferedImage image = getScaledImage(ImageIO.read(fis), widthField, heightField); //reading the image file

        //3. customization for the specific project 
        int chunks = rows * cols;
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight() / rows;
        
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; 
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }

        for (int i = 0; i < imgs.length; i++) {
        	//4.modified by me
            ImageIO.write(imgs[i], "jpg", new File(System.getProperty("user.dir")+ "//SplitedPictures//" +"img" + i + ".jpg"));
        }
    }

	public static File getFileInProjectDir(String path) {
		return new File(System.getProperty("user.dir")+ "//Picture//" + path + ".jpg");
	}
	
	public static File getFile(String path){
		return new File(path);
	}
	
    public static BufferedImage loadFullImage(String path, boolean isGlobalPath) throws IOException{
    	BufferedImage imageIcon;
    	if (isGlobalPath) {
			imageIcon = ImageIO.read(getFile(path));
		}else{
			System.out.println(getFileInProjectDir(path).getAbsolutePath());
			imageIcon = (ImageIO.read(getFileInProjectDir(path)));
		}
		return imageIcon;
    }

    
    public static ImageIcon[] loadImages() throws Exception{
    	ImageIcon[] images = new ImageIcon[rows*cols];
    	for (int j = 0; j < (rows*cols); j++) {
    		images[j] = new IconExtension(ImageIO.read(new File(
    				System.getProperty("user.dir")+ "//SplitedPictures//" + "img" + j + ".jpg")), j); 
    	}
    	return images;
    }
    
    // Quelle : http://stackoverflow.com/questions/3967731/how-to-improve-the-performance-of-g-drawimage-method-for-resizing-images
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth  = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double)width/imageWidth;
        double scaleY = (double)height/imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
            image,
            new BufferedImage(width, height, image.getType()));
    }
    
}