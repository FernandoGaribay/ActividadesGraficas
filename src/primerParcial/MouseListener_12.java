package primerParcial;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MouseListener_12 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> generarJFrame());
    }

    private static void generarJFrame() {
        JFrame frame = new JFrame("Eventos del MouseListener");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse Clicked at: (" + e.getX() + ", " + e.getY() + ")");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse Entered");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse Exited");
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse Pressed");
            }
            
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse Released");
            }
        }
        );

        frame.setVisible(true);
    }
}
