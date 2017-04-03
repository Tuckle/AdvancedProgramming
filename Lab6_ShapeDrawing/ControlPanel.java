package AdvancedProgramming.Lab6_ShapeDrawing;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gbalan on 4/2/2017.
 */
public class ControlPanel {
    JPanel buttonPanel;
    JButton saveButton;
    JButton loadButton;
    JButton resetButton;
    Canvas  inputCanvas;
    public ControlPanel(Canvas cs) {
        this.inputCanvas = cs;
        buttonPanel = new JPanel();
        saveButton = new JButton();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write(inputCanvas.image, "png", new File("save.png"));
                } catch (IOException exp) {
                    // TODO Auto-generated catch block
                    exp.printStackTrace();
                }
            }
        });
        saveButton.setText("Save");
        loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inputCanvas.image = ImageIO.read(new File("save.png"));
                    inputCanvas.setTempValue(0);
                    inputCanvas.repaint();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCanvas.setTempValue(0);
                inputCanvas.image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
                inputCanvas.repaint();
            }
        });
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(resetButton);
    }

    public JPanel getControlPanel(){
        return buttonPanel;
    }
}
