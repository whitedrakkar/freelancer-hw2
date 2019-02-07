
import java.awt.Color;

public class ImageCross extends ImageTemplate {

    private final int[][] circle = {
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0},
        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0},
        {0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0},
        {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
        {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}};

    ImageCross(Color color1, Color color2, int originalX, int originalY) {
        super(color1, color2, originalX, originalY);
    }

    @Override
    protected int[][] getData() {
        return circle;
    }
}
