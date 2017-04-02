import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
        saveButton.setText("Save");
        loadButton = new JButton();
        loadButton.setText("Load");
        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputCanvas.setTempValue(false);
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
