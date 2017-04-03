package AdvancedProgramming.Lab6_ShapeDrawing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gbalan on 4/2/2017.
 */
public class Toolbar {
    JLabel numberOfSides;
    JLabel numberOfShapes;
    JLabel stroke;
    JButton drawButton;
    JSpinner sidesSpineer;
    JSpinner shapesSpineer;
    JSpinner strokeSpineer;
    JPanel mainPanel;
    Canvas  inputCanvas;
    public Toolbar(Canvas itr)
    {
        inputCanvas = itr;
        mainPanel = new JPanel();
        numberOfSides = new JLabel();
        numberOfSides.setText("Number of sides:");
        numberOfShapes = new JLabel();
        numberOfShapes.setText("Number of shapes:");
        stroke = new JLabel();
        stroke.setText("Stroke");
        drawButton = new JButton();
        drawButton.setText("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int shp = Integer.parseInt(shapesSpineer.getValue().toString());
                int sid = Integer.parseInt(sidesSpineer.getValue().toString());
                int str  = Integer.parseInt(strokeSpineer.getValue().toString());
                inputCanvas.setCustomStuff(shp, sid, str);
                inputCanvas.setTempValue(2);
                inputCanvas.repaint();
            }
        });
        sidesSpineer = new JSpinner();
        mainPanel.add(numberOfSides);
        mainPanel.add(sidesSpineer);
        shapesSpineer = new JSpinner();
        mainPanel.add(numberOfShapes);
        mainPanel.add(shapesSpineer);
        strokeSpineer = new JSpinner();
        mainPanel.add(stroke);
        mainPanel.add(strokeSpineer);
        mainPanel.add(drawButton);
    }

    public JPanel getPannel(){
        return mainPanel;
    }

}
