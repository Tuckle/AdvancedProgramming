import com.intellij.*;
import org.jaxen.function.FalseFunction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

/**
 * Created by gbalan on 4/2/2017.
 */
public class Canvas extends JPanel{
    Graphics2D graphics;
    BufferedImage image;
    int counter;
    int x, y, radius, sides;
    boolean tempValue = false;
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
                    tempValue = true;
                    repaint();
                }
            });
    }

    public void setTempValue(boolean inputValue)
    {
        tempValue = inputValue;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D newD = (Graphics2D) g;
        if (tempValue == true)
        {
            Random rand = new Random();
            radius = rand.nextInt(200);
            sides = rand.nextInt(300);
            float tempStroke = rand.nextFloat();
            newD.setStroke(new BasicStroke(tempStroke, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f));
            newD.setColor(new Color(rand.nextInt(0xFFFFFF)));
            newD.fill(new RegularPolygon(x, y, radius, sides));
        }
    }

    public Canvas getCanvas()
    {
        return this;
    }
}
