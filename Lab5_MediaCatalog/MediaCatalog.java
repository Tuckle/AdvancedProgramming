package Lab5_MediaCatalog;
//package AdvancedProgramming.Lab5_MediaCatalog;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import AdvancedProgramming.Lab4.Sources.*;
import net.sf.dynamicreports.report.defaults.Default;

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
    private JTree treeList;
    private JButton Play;
    private JButton Report;
    private Catalog items;

    public MediaCatalog() {
        items = new Catalog();
        $$$setupUI$$$();
        treeList.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Objects")));
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
//                    DefaultListModel infoList = new DefaultListModel();
//                    ListModel tempModel = catalogItems.getModel();
//                    for (int i = 0; i < tempModel.getSize(); i++) {
//                        if (newItem.equals(tempModel.getElementAt(i))) {
//                            // element already in list
//                            return;
//                        }
////                        infoList.addElement(tempModel.getElementAt(i));
//                    }
                    Song newSong = new Song(name, path, year, "Unknown");
                    try {
                        items.add(newSong);
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
//                    infoList.addElement(newItem);
////                    catalogItems.setModel(infoList);
                    //                    if (root.isLeaf()) {
//                        DefaultMutableTreeNode s = new DefaultMutableTreeNode("Songs");
//                        root.add(s);
//                        s = new DefaultMutableTreeNode("Books");
//                        root.add(s);
//                        s = new DefaultMutableTreeNode("Movies");
//                        root.add(s);
//                    }

                    DefaultTreeModel tempTreeModel = (DefaultTreeModel) treeList.getModel();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode) tempTreeModel.getRoot();

                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(name);
                    DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(path);
                    newNode.add(tempNode);
                    tempNode = new DefaultMutableTreeNode(year);
                    newNode.add(tempNode);
                    DefaultMutableTreeNode song = new DefaultMutableTreeNode(newItem);
                    root.add(newNode);
//                    root.add(rootTree);
                    tempTreeModel.setRoot(root);
//                    tempTreeModel.insertNodeInto(new DefaultMutableTreeNode(newSong), root, root.getChildCount());

                    treeList.setModel(tempTreeModel);
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
                    DefaultTreeModel tempTreeModel = (DefaultTreeModel) treeList.getModel();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode) tempTreeModel.getRoot();
                    for (AbstractItem newItem : items.getItemsList()) {
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newItem.getName());
                        DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(newItem.getPath());
                        newNode.add(tempNode);
                        tempNode = new DefaultMutableTreeNode(newItem.getYear());
                        newNode.add(tempNode);
                        root.add(newNode);
                    }
                    tempTreeModel.setRoot(root);
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
        Play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (nameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Play error! Name not given.");
                    return;
                }
                PlayCommand pl = new PlayCommand(items, nameField.getText());
                try {
                    pl.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            }
        });
        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (items.getItemsList().size() == 0) {
                    JOptionPane.showMessageDialog(null, "No items in list, could not create report with 0 items.");
                    return;
                }
                String type = "pdf";
                if (nameField.getText().length() != 0) {
                    if (nameField.getText().toLowerCase().equals("pdf")) {
                        type = "pdf";
                    } else if (nameField.getText().toLowerCase().equals("html")) {
                        type = "html";
                    } else {
                        JOptionPane.showMessageDialog(null, "Name has not matching report types(pdf or html). Try again or leave it blank!");
                        return;
                    }
                }
                ReportCommand cmd = new ReportCommand(items, type);
                try {
                    cmd.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CustomException e) {
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
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
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treeList = new JTree();
        treeList.putClientProperty("JTree.lineStyle", "");
        scrollPane1.setViewportView(treeList);
        Play = new JButton();
        Play.setSelected(true);
        Play.setText("Play");
        panel1.add(Play, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Report = new JButton();
        Report.setText("Report");
        panel1.add(Report, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
