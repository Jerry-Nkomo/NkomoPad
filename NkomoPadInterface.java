package nkomo.gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author Nkomo Jerry
 */
public class NkomoPadInterface extends JFrame {

    private JTextArea txtArea;
    private JMenuBar mnBar;
    private JMenu mnFile, mnEdit, mnFont;
    private JMenuItem mnShrink, mnEnlarge, mnBold, mnItalic, mnPlain, mnBoldItalic;
    private JMenuItem mnOpen, mnSave, mnNew, mnCopy, mnCut, mnCopyAll, mnPaste, mnClose;
    private JFileChooser fileOpen, fileSave;
    private int fileToOpen, fileToSave;

    public NkomoPadInterface() {
        txtArea = new JTextArea();
        mnBar = new JMenuBar();
        setJMenuBar(mnBar);

        //Menus
        mnFile = new JMenu("File");
        mnEdit = new JMenu("Edit");
        mnFont = new JMenu("Font");

        //Menu Items
        mnOpen = new JMenuItem("Open");
        mnSave = new JMenuItem("Save");
        mnNew = new JMenuItem("New");
        mnCopy = new JMenuItem("Copy");
        mnCut = new JMenuItem("Cut");
        mnCopyAll = new JMenuItem("Copy All");
        mnPaste = new JMenuItem("Paste");
        mnClose = new JMenuItem("Close");
        mnShrink = new JMenuItem("Shrink");
        mnEnlarge = new JMenuItem("Enlarge");
        mnBold = new JMenuItem("Bold");
        mnItalic = new JMenuItem("Italic");
        mnPlain = new JMenuItem("Plain");
        mnBoldItalic = new JMenuItem("Bold and Italic");

        //add fileMenu, fontMenu and editmenu to the menuBar
        mnBar.add(mnFile);
        mnBar.add(mnEdit);
        mnBar.add(mnFont);

        //Add menuItems to the menu
        mnFile.add(mnNew);
        mnFile.add(mnOpen);
        mnFile.add(mnSave);
        mnFile.add(mnClose);
        mnEdit.add(mnCut);
        mnEdit.add(mnCopy);
        mnEdit.add(mnCopyAll);
        mnEdit.add(mnPaste);
        mnFont.add(mnShrink);
        mnFont.add(mnEnlarge);
        mnFont.add(mnBold);
        mnFont.add(mnItalic);
        mnFont.add(mnPlain);
        mnFont.add(mnBoldItalic);

        //Add actionListener to the menuItems
        mnNew.addActionListener(new mnNewListener());
        mnOpen.addActionListener(new mnOpenListener());
        mnSave.addActionListener(new mnSaveListener());
        mnCut.addActionListener(new mnCutListener());
        mnCopy.addActionListener(new mnCopyListener());
        mnCopyAll.addActionListener(new mnCopyAllListener());
        mnPaste.addActionListener(new mnPasteListener());
        mnClose.addActionListener(new mnCloseListener());
        mnShrink.addActionListener(new mnShrinkListener());
        mnEnlarge.addActionListener(new mnEnlargeListener());
        mnBold.addActionListener(new mnBoldListener());
        mnItalic.addActionListener(new mnItalicListener());
        mnPlain.addActionListener(new PlainListener());
        mnBoldItalic.addActionListener(new mnBoldItalicListener());

        Container screen = getContentPane();
        screen.add(txtArea);

    }

    public void openFile() {
        JFileChooser open = new JFileChooser();
        int option = open.showOpenDialog(this);
        fileToOpen = option;
        fileOpen = open;

    }

    public void saveFile() {
        JFileChooser save = new JFileChooser();
        int option = save.showSaveDialog(this);
        fileToSave = option;
        fileSave = save;

    }

    private static class mnCloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

    private class mnBoldListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int size;
            Font font = txtArea.getFont();
            size = font.getSize();
            Font newFont = new Font(null, Font.BOLD, size);
            txtArea.setFont(newFont);

        }
    }

    private class mnItalicListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            int size;
            Font font = txtArea.getFont();
            size = font.getSize();
            Font newFont = new Font(null, Font.ITALIC, size);
            txtArea.setFont(newFont);
        }
    }

    private class PlainListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int size;
            Font font = txtArea.getFont();
            size = font.getSize();
            Font newFont = new Font(null, Font.PLAIN, size);
            txtArea.setFont(newFont);
        }
    }

    private class mnBoldItalicListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int size;
            Font font = txtArea.getFont();
            size = font.getSize();
            Font newFont = new Font(null,Font.BOLD + Font.ITALIC,size);
            txtArea.setFont(newFont);

        }
    }

    private class mnShrinkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int shrink;
            Font font = txtArea.getFont();
            shrink = font.getSize() - 2;
            Font newFont = new Font(null, Font.PLAIN, shrink);
            txtArea.setFont(newFont);

        }
    }

    private class mnEnlargeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int enlarge;
            Font font = txtArea.getFont();
            enlarge = font.getSize() + 2;
            Font newFont = new Font(null, Font.PLAIN, enlarge);
            txtArea.setFont(newFont);

        }
    }

    private class mnNewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtArea.setText("");

        }
    }

    private class mnOpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //Call the OpenFile Method
            openFile();
            if (fileToOpen == JFileChooser.APPROVE_OPTION) {
                txtArea.setText("");
                try {
                    Scanner sc = new Scanner(new FileReader(fileOpen.getSelectedFile().getPath()));
                    while (sc.hasNext()) {
                        txtArea.append(sc.nextLine());
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());

                }

            }

        }
    }

    private class mnSaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //Call the saveFile method
            saveFile();
            if (fileToSave == JFileChooser.APPROVE_OPTION) {
                try {
                    PrintWriter write = new PrintWriter(new FileWriter(fileSave.getSelectedFile().getPath()));
                    write.write(txtArea.getText());
                    write.close();
                } catch (IOException ex) {

                }
            }

        }
    }

    private class mnCutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtArea.cut();

        }

    }

    private class mnCopyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtArea.copy();

        }
    }

    private class mnCopyAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtArea.selectAll();
            txtArea.copy();

        }
    }

    private class mnPasteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtArea.paste();
        }
    }

}
