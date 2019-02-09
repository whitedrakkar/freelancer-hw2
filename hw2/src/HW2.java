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
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HW2 extends JPanel {

    // A counter that increases by one in each frame.
    private int frameNumber;

    private Transformation transformation;

    private final ImageTemplate letter = ImageCreator.createImage("letter", Color.RED, Color.BLACK, 0, 0);
    private final ImageTemplate rectangle = ImageCreator.createImage("rectangle", Color.BLACK, Color.YELLOW, 60, 60);
    private final ImageTemplate circle = ImageCreator.createImage("circle", Color.YELLOW, Color.BLUE, 60, 120);
    private final ImageTemplate number = ImageCreator.createImage("number", Color.PINK, Color.LIGHT_GRAY, 120, 60);
    private final ImageTemplate cross = ImageCreator.createImage("cross", Color.GREEN, Color.ORANGE, 60, -60);

    private boolean firstDraw = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame window;
        window = new JFrame("Java Animation");  // The parameter shows in the window title bar.
        final HW2 panel = new HW2(); // The drawing area.
        window.setContentPane(panel); // Show the panel in the window.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // End program when window closes.
        window.pack();  // Set window size based on the preferred sizes of its contents.
        window.setResizable(false); // Don't let user resize window.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation( // Center window on screen.
                (screen.width - window.getWidth()) / 2,
                (screen.height - window.getHeight()) / 2);
        Timer animationTimer;  // A Timer that will emit events to drive the animation.

        // Taken from AnimationStarter
        // Modified to change timing and allow for recycling
        animationTimer = new Timer(1600, (ActionEvent arg) -> {
            if (panel.frameNumber > 4) {
                panel.frameNumber = 0;
            } else {
                panel.frameNumber++;
            }

            panel.repaint();
        });
        window.setVisible(true); // Open the window, making it visible on the screen.
        animationTimer.start();  // Start the animation running.
    }

    public HW2() {
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
        System.out.print("Frame is " + frameNumber);
        switch (frameNumber) {
            case 0:
                transformation = new Transformation();
                System.out.println(". Do nothing.");
                break;

            case 1:
                transformation = new Transformation(-12, 12, 1, 1, 0);
                System.out.println(". Translate (-12,12).");
                break;

            case 2:
                transformation = new Transformation(0, 0, 1, 1, 55 * Math.PI / 180.0);
                System.out.println(". Rotate (55) counter-clockwise.");
                break;

            case 3:
                transformation = new Transformation(0, 0, 1, 1, -75 * Math.PI / 180.0);
                System.out.println(". Rotate (75) clockwise.");
                break;

            case 4:
                transformation = new Transformation(0, 0, 3, 1.5, 0);
                System.out.println(". Scale up (3,1.5).");
                break;

            case 5:
                // added to not make things escalate so quickly
                transformation = new Transformation(0, 0, 0.5, 1, 0);
                System.out.println(". Scale down (0.5,1).");
                break;

            default:
                break;
        } // End switch

        if (firstDraw) {

            g2.translate(400, 300); // Set image into position.
            AffineTransform originalTransform = (AffineTransform) g2.getTransform().clone();

            drawFirstImage(g2, rectangle, originalTransform);
            drawFirstImage(g2, circle, originalTransform);
            drawFirstImage(g2, letter, originalTransform);
            drawFirstImage(g2, number, originalTransform);
            drawFirstImage(g2, cross, originalTransform);

            firstDraw = false;

        } else {

            drawImage(g2, rectangle);
            drawImage(g2, circle);
            drawImage(g2, letter);
            drawImage(g2, number);
            drawImage(g2, cross);

        }
    }

    private void drawFirstImage(Graphics2D g2, ImageTemplate imageTemplate, AffineTransform originalTransform) {

        g2.setTransform(originalTransform); // revert to original transform
        g2.translate(imageTemplate.getOriginalX(), imageTemplate.getOriginalY()); // Set image into position.
        g2.drawImage(imageTemplate.getImage(), 0, 0, this); // Draw image.
        imageTemplate.setTransform((AffineTransform) g2.getTransform().clone()); // remember last transform for this iage.
    }

    private void drawImage(Graphics2D g2, ImageTemplate imageTemplate) {
        AffineTransform transform = imageTemplate.getTransform();
        g2.setTransform(transform);

        g2.translate(transformation.getTranslateX(), transformation.getTranslateY()); // Move image.
        g2.rotate(transformation.getRotation()); // Rotate image.
        g2.scale(transformation.getScaleX(), transformation.getScaleY()); // Scale image.

        g2.drawImage(imageTemplate.getImage(), 0, 0, this); // Draw image.

        imageTemplate.setTransform((AffineTransform) g2.getTransform().clone());
    }
}
