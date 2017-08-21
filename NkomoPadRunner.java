package nkomo.gui;

import javax.swing.JFrame;

/**
 *
 * @author Jerry Nkomo
 */
public class NkomoPadRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame screen = new NkomoPadInterface();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setTitle("Nkomopad Editor");
        screen.setSize(500,500);
        screen.setVisible(true);
        

    }
}
