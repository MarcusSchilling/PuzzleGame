package test;

import image.IconExtension;
import image.ImageSplit;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static picture.Constant.cols;
import static picture.Constant.rows;

/**
 * Created by schilling on 13.02.17.
 */
class ImageSplitTest {

    @Test
    public void testName() throws Exception {
        ImageSplit imageSplitTest = new ImageSplit();
        ImageSplit.split("1", false);
        for (ImageIcon icon : Arrays.asList(ImageSplit.loadImages())) {
            IconExtension extension =(IconExtension) icon;
            System.out.println(extension.getTargetPosition());
        }
        for (JLabel label : putInLabels(Arrays.asList(imageSplitTest.loadImages()))) {
            IconExtension extension = (IconExtension)label.getIcon();
            System.out.println(extension.getTargetPosition());
        }

        assertEquals(imageSplitTest.loadImages().length, cols*rows);
    }

    private JLabel[] putInLabels(List<ImageIcon> list){
        JLabel[] labels = new JLabel[cols*rows];
        int count = 0;
        for (ImageIcon imageIcon : list) {
            labels[count] = new JLabel();
            labels[count].setIcon(imageIcon);
            count++;
        }
        return labels;
    }

}