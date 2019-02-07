
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class ImageTemplate {

    private AffineTransform transform;
    private Color color1;
    private Color color2;
    private int originalX;
    private int originalY;

    public ImageTemplate() {
    }

    public ImageTemplate(Color color1, Color color2, int originalX, int originalY) {
        this.color1 = color1;
        this.color2 = color2;
        this.originalX = originalX;
        this.originalY = originalY;
    }

    public BufferedImage getImage() {

        int[][] pixelMatrix = getData();

        int sizeX = pixelMatrix.length;
        int sizeY = pixelMatrix[0].length;

        BufferedImage image = new BufferedImage(sizeX, sizeY,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                int pixelColor = pixelMatrix[x][y];
                // Set Colors based on Binary Image value
                if (pixelColor == 0) {
                    pixelColor = color2.getRGB();
                } else {
                    pixelColor = color1.getRGB();
                }
                image.setRGB(x, y, pixelColor);
            } // End for y.
        } // End for x.
        return image;
    }

    protected abstract int[][] getData();

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public int getOriginalX() {
        return originalX;
    }

    public int getOriginalY() {
        return originalY;
    }
}
