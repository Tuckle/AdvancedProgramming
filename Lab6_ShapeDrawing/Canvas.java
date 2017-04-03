package AdvancedProgramming.Lab6_ShapeDrawing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by gbalan on 4/2/2017.
 */
public class Canvas extends JPanel{
    Graphics2D graphics;
    BufferedImage image;
    int counter;
    int x, y, radius, sides;
    int tempValue = 0;
    float tempStroke;
    Random rand;
    int numberOfShapes;
    int numberOfSides;
    int givenStroke;

    public void drawShape()
    {
        if (tempValue == 1)
        {
            Graphics2D tempGG = image.createGraphics();
            rand = new Random();
            radius = rand.nextInt(100);
            sides = rand.nextInt(10);
            float tempStroke = rand.nextFloat();
            tempGG.setStroke(new BasicStroke(tempStroke, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f));
            tempGG.setColor(new Color(rand.nextInt(0xFFFFFF)));
            tempGG.fill(new RegularPolygon(x, y, radius, sides));
        }
        else if (tempValue == 2)
        {
            int counter = 0;
            image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            Graphics2D tempGG = image.createGraphics();
            while (counter < this.numberOfShapes)
            {
                rand = new Random();
                int randX = rand.nextInt(800);
                int randY = rand.nextInt(600);
                tempGG.setColor(new Color(rand.nextInt(0xFFFFFF)));
                radius = rand.nextInt(100);
                tempGG.setStroke(new BasicStroke(this.givenStroke));
                tempGG.fill(new RegularPolygon(randX, randY, radius, this.numberOfSides ));
                counter += 1;
            }
        }
    }

    public Canvas()
    {
        counter = 10;
        setBackground(Color.white);
        image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        setPreferredSize(new Dimension(800, 600));
        this.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e) {
                    x = e.getX();
                    y = e.getY();
                    tempValue = 1;
                    repaint();
                }
            });
    }

    public void setTempValue(int inputValue)
    {
        tempValue = inputValue;
    }

    public void setCustomStuff(int numberOfShapes, int numberOfSides, int givenStroke)
    {
        this.numberOfShapes = numberOfShapes;
        this.numberOfSides = numberOfSides;
        this.givenStroke = givenStroke;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D newD = (Graphics2D) g;
        drawShape();
        newD.drawImage(image, 0, 0, null);
    }

    public Canvas getCanvas()
    {
        return this;
    }
}
