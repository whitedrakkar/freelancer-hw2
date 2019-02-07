/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author jim
 */
public class CMSC325Animate extends JPanel {

    // A counter that increases by one in each frame.
    private int frameNumber;
    // The time, in milliseconds, since the animation started.
    private long elapsedTimeMillis;
    // This is the measure of a pixel in the coordinate system
    // set up by calling the applyLimits method.  It can be used
    // for setting line widths, for example.
    private float pixelSize;

    private Transformation transformation;

    private final ImageTemplate letter = ImageCreator.createImage("letter", Color.RED, Color.BLACK, 0, 0);
    private final ImageTemplate rectangle = ImageCreator.createImage("rectangle", Color.BLACK, Color.YELLOW, 20, 20);
    private final ImageTemplate circle = ImageCreator.createImage("circle", Color.YELLOW, Color.BLUE, 20, 40);
    private final ImageTemplate number = ImageCreator.createImage("number", Color.PINK, Color.LIGHT_GRAY, 40, 20);
    private final ImageTemplate cross = ImageCreator.createImage("cross", Color.GREEN, Color.ORANGE, 20, -20);

    private boolean firstDraw = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window;
        window = new JFrame("Java Animation");  // The parameter shows in the window title bar.
        final CMSC325Animate panel = new CMSC325Animate(); // The drawing area.
        window.setContentPane(panel); // Show the panel in the window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
        window.pack();  // Set window size based on the preferred sizes of its contents.
        window.setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth()) / 2,
                (screen.height - window.getHeight()) / 2);
        Timer animationTimer;  // A Timer that will emit events to drive the animation.
        final long startTime = System.currentTimeMillis();
        // Taken from AnimationStarter
        // Modified to change timing and allow for recycling
        animationTimer = new Timer(1600, (ActionEvent arg) -> {
            if (panel.frameNumber > 4) {
                panel.frameNumber = 0;
            } else {
                panel.frameNumber++;
            }
            panel.elapsedTimeMillis = System.currentTimeMillis() - startTime;
            panel.repaint();
        });
        window.setVisible(true); // Open the window, making it visible on the screen.
        animationTimer.start();  // Start the animation running.
    }

    public CMSC325Animate() {
        // Size of Frame
        setPreferredSize(new Dimension(800, 600));
    }

    // This is where all of the action takes place
    // Code taken from AnimationStarter.java but modified to add the specific Images
    // Also added looping structure for Different transformations
    @Override
    protected void paintComponent(Graphics g) {

        /* First, create a Graphics2D drawing context for drawing on the panel.
         * (g.create() makes a copy of g, which will draw to the same place as g,
         * but changes to the returned copy will not affect the original.)
         */
        Graphics2D g2 = (Graphics2D) g.create();

        /* Turn on antialiasing in this graphics context, for better drawing.
         */
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Fill in the entire drawing area with white.
         */
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight()); // From the old graphics API!

        // Controls your zoom and area you are looking at
        //applyWindowToViewportTransformation(g2, -75, 75, -75, 75, true);
        System.out.println("Frame is " + frameNumber);
        switch (frameNumber) {
            case 0:
                transformation = new Transformation();
                break;

            case 1:
                transformation = new Transformation(-12, 12, 1, 1, 0);
                break;

            case 2:
                transformation = new Transformation(0, 0, 1, 1, 55 * Math.PI / 180.0);
                break;

            case 3:
                transformation = new Transformation(0, 0, 1, 1, -75 * Math.PI / 180.0);
                break;

            case 4:
                transformation = new Transformation(0, 0, 3, 1.5, 0);
                break;

            case 5:
                // added to not make things escalate so quickly
                transformation = new Transformation(0, 0, 0.5, 1, 0);
                break;

            default:
                break;
        } // End switch

        if (firstDraw) {

            g2.translate(400, 300); // Set image into position.

            drawImage(g2, rectangle);
            drawImage(g2, circle);
            drawImage(g2, letter);
            //initImage(g2, number);
            //initImage(g2, cross);
            firstDraw = false;

            letter.setTransform(g2.getTransform());
        } else {

            AffineTransform transform = letter.getTransform();
            g2.setTransform(transform);

            g2.translate(transformation.getTranslateX(), transformation.getTranslateY()); // Move image.
            g2.rotate(transformation.getRotation()); // Rotate image.
            g2.scale(transformation.getScaleX(), transformation.getScaleY()); // Scale image.

            drawImage(g2, rectangle);
            drawImage(g2, circle);
            drawImage(g2, letter);
            //drawImage(g2, number);
            //drawImage(g2, cross);

            letter.setTransform(g2.getTransform());
        }
    }

    private void drawImage(Graphics2D g2, ImageTemplate imageTemplate) {
        g2.translate(imageTemplate.getOriginalX(), imageTemplate.getOriginalY()); // offset image.
        g2.drawImage(imageTemplate.getImage(), 0, 0, this); // Draw image.
        g2.translate(-imageTemplate.getOriginalX(), -imageTemplate.getOriginalY()); // offset image.
    }
}
