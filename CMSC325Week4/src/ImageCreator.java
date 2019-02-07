
import java.awt.Color;

public class ImageCreator {

    /**
     * Creates an image of given type
     *
     * @param type the type
     * @return
     */
    public static ImageTemplate createImage(String type, Color color1, Color color2, int originalX, int originalY) {
        switch (type) {
            case "letter":
                return new ImageLetter(color1, color2, originalX, originalY);
            case "rectangle":
                return new ImageRectangle(color1, color2, originalX, originalY);
            case "circle":
                return new ImageCircle(color1, color2, originalX, originalY);
            case "number":
                return new ImageNumber(color1, color2, originalX, originalY);
            case "cross":
                return new ImageCross(color1, color2, originalX, originalY);
            default:
                return null;
        }
    }
}
