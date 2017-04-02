import javax.swing.*;

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
    public Toolbar()
    {
        mainPanel = new JPanel();
        numberOfSides = new JLabel();
        numberOfSides.setText("Number of sides:");
        numberOfShapes = new JLabel();
        numberOfShapes.setText("Number of shapes:");
        stroke = new JLabel();
        stroke.setText("Stroke");
        drawButton = new JButton();
        drawButton.setText("Draw");
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
