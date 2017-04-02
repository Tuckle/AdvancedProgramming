package Lab5_MediaCatalog;
//package AdvancedProgramming.Lab5_MediaCatalog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import AdvancedProgramming.Lab4.Sources.Catalog;
import AdvancedProgramming.Lab4.Sources.AbstractItem;
import AdvancedProgramming.Lab4.Sources.CustomException;
import AdvancedProgramming.Lab4.Sources.Song;

/**
 * Created by apiriu on 3/25/2017.
 */
public class MediaCatalog {
    private JList catalogItems;
    public JPanel panel1;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addButton;
    private JSpinner yearSpinner;
    private JLabel yearLabel;
    private JTextField pathField;
    private JLabel pathLabel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JTable table1;
    private Catalog items;

    public MediaCatalog() {
        items = new Catalog();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = nameField.getText();
                String path = pathField.getText();
                Integer year = Integer.parseInt(yearSpinner.getValue().toString());
                if (name.length() == 0) {
                    // name not given
                    JOptionPane.showMessageDialog(null, "Please insert name, textbox mandatory!");
                    return;
                } else if (path.length() == 0) {
                    // path not given
                    JOptionPane.showMessageDialog(null, "Please insert path, textbox mandatory!");
                    return;
                } else if (year == 0) {
                    // year not given
                    JOptionPane.showMessageDialog(null, "Please pick year, it is mandatory!");
                    return;
                } else {
                    // all output is ok
                    String newItem = name + ", " + year + " [" + path + "]";
                    DefaultListModel infoList = new DefaultListModel();
                    ListModel tempModel = catalogItems.getModel();
                    for (int i = 0; i < tempModel.getSize(); i++) {
                        if (newItem.equals(tempModel.getElementAt(i))) {
                            // element already in list
                            return;
                        }
                        infoList.addElement(tempModel.getElementAt(i));
                    }
                    Song newSong = new Song(name, path, year, "Unknown");
                    try {
                        items.add(newSong);
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                    infoList.addElement(newItem);
                    catalogItems.setModel(infoList);
                    return;
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String path = pathField.getText();
                if (path.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Load error! Path not given.");
                    return;
                }
                File f = new File(path);
                if (!f.exists() || !f.isFile()) {
                    JOptionPane.showMessageDialog(null, "Input path does not exist! Try again.");
                    return;
                }
                try {
                    items.load(path);
                    DefaultListModel infoList = new DefaultListModel();
                    ListModel tempModel = catalogItems.getModel();
                    for (AbstractItem newItem : items.getItemsList()) {
                        infoList.addElement(newItem.getName() + ", " + newItem.getYear() + " [" + newItem.getPath() + "]");
                    }
                    catalogItems.setModel(infoList);
                    JOptionPane.showMessageDialog(null, "Data successfully loaded from " + path);
                } catch (CustomException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String path = pathField.getText();
                if (path.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Load error! Path not given.");
                    return;
                }
                try {
                    items.save(path);
                    JOptionPane.showMessageDialog(null, "Data successfully saved to " + path);
                } catch (CustomException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("MediaCatalog");
        fr.setContentPane(new MediaCatalog().panel1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMaximumSize(new Dimension(1920, 1080));
        panel1.setMinimumSize(new Dimension(1024, 720));
        panel1.setPreferredSize(new Dimension(1024, 720));
        panel1.setBorder(BorderFactory.createTitledBorder("Add media Item"));
        loadButton = new JButton();
        loadButton.setText("Load");
        panel1.add(loadButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Save");
        panel1.add(saveButton, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        panel1.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        yearLabel = new JLabel();
        yearLabel.setText("Year");
        panel1.add(yearLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearSpinner = new JSpinner();
        panel1.add(yearSpinner, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(65, -1), null, null, 0, false));
        pathField = new JTextField();
        panel1.add(pathField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        pathLabel = new JLabel();
        pathLabel.setText("Path");
        panel1.add(pathLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        panel1.add(nameField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("Name");
        panel1.add(nameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        table1 = new JTable();
        panel1.add(table1, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
