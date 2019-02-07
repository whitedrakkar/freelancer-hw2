
public class Transformation {

    private int translateX = 0;
    private int translateY = 0;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double rotation = 0;

    public Transformation() {
    }

    public Transformation(int translateX, int translateY, double scaleX, double scaleY, double rotation) {
        this.translateX = translateX;
        this.translateY = translateY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.rotation = rotation;
    }

    public int getTranslateX() {
        return translateX;
    }

    public int getTranslateY() {
        return translateY;
    }

    public double getScaleX() {
        return scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public double getRotation() {
        return rotation;
    }
}
