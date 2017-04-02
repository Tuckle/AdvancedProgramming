package AdvancedProgramming.Lab5_MediaCatalog;

import javax.swing.*;

/**
 * Created by apiriu on 3/27/2017.
 */
public class MediaFrame {
    private JButton addButton;
    private JSpinner yearSpinner;
    private JLabel yearLabel;
    private JTextField pathField;
    private JLabel pathLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JFrame fr;

    public MediaFrame() {
        JFrame fr = new JFrame("MediaCatalog");
        fr.setContentPane(new Lab5_MediaCatalog.MediaCatalog().panel1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    }
}
