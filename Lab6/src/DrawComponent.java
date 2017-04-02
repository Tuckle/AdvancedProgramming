import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by gbalan on 4/2/2017.
 */
public class DrawComponent extends JComponent {

    Graphics2D graphics;
    int x;
    int y;
    int radius;
    int sides;
    public DrawComponent(int x, int y, int radius, int sides) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.sides = sides;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Random rand = new Random();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(rand.nextInt(0xFFFFFF)));
        RegularPolygon p = new RegularPolygon(x, y, radius, sides);
        g2d.draw(p);
        g2d.fill(p);
        g2d.drawRect(200, 200, 200, 200);
    }
}
