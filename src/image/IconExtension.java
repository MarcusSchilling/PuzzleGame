package image;

import javax.swing.*;
import java.awt.*;

public class IconExtension extends ImageIcon {

    private static final long serialVersionUID = 1L;

    private final int targetPosition;

    public IconExtension(Image image, int targetPosition) {
        super(image);
        this.targetPosition = targetPosition;
    }

    public int getTargetPosition() {
        return targetPosition;
    }
}
