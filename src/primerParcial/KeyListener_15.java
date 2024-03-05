package primerParcial;

import javax.swing.*;
import java.awt.event.*;

public class KeyListener_15 extends JPanel {

    public KeyListener_15() {
        JPanel panel = new JPanel();
        JTextField textField = new JTextField("Key listener", 20);

        panel.add(textField);
        add(panel);

        addListeners(textField);
    }

    private void addListeners(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("- (keyPressed) Presionaste la tecla: " + e.getKeyChar());
            }

            public void keyReleased(KeyEvent e) {
                System.out.println("- (keyReleased) Soltaste la tecla: " + e.getKeyChar());
            }

            public void keyTyped(KeyEvent e) {
                System.out.println("- (keyTyped) Tipeaste la tecla: " + e.getKeyChar());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Key Listener");

        frame.setSize(400, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        KeyListener_15 ventana = new KeyListener_15();
        frame.add(ventana);
        frame.setVisible(true);
    }
}
