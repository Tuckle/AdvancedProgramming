package AdvancedProgramming.Lab6_ShapeDrawing;
import javax.swing.*;
import java.awt.*;

/**
 * Created by gbalan on 4/2/2017.
 */
public class DrawingFrame {
    JFrame frame;
    public DrawingFrame()
    {
        frame = new JFrame("DrawingForm");
        frame.setLayout(new BorderLayout(4, 4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1024, 720));
        frame.setResizable(false);
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public void pack(){
        frame.pack();
    }

    public void show(){
        frame.setVisible(true);
    }
}
