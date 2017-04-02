import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by gbalan on 4/2/2017.
 */
public class Main {
    public static void main(String[] args) {
        DrawingFrame dFrame = new DrawingFrame();
        JFrame frame = dFrame.getFrame();
        Canvas  dCanvas = new Canvas();
        frame.getContentPane().add(dCanvas, BorderLayout.CENTER);
        ControlPanel controlPanel = new ControlPanel(dCanvas);
        JPanel panel = controlPanel.getControlPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        Toolbar toolbar = new Toolbar();
        JPanel secondPanel = toolbar.getPannel();
        frame.getContentPane().add(secondPanel, BorderLayout.NORTH);
        frame.pack();
        frame.show();
    }
}

