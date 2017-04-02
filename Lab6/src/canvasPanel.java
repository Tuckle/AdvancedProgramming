import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by gbalan on 4/2/2017.
 */
public class canvasPanel extends JPanel{
    int x;
    int y;
    int sides;
    int radius;
    canvasPanel() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(420,420));
    }

    public void setStuff(int x, int y, int radius, int sides){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.sides = sides;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.paintComponent(g);
        Random rand = new Random();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(rand.nextInt(0xFFFFFF)));
        RegularPolygon p = new RegularPolygon(x, y, radius, sides);
//        g2d.draw(p);
        g2d.fill(p);
    }
}

