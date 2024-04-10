package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BersenhamCircunferencias_11 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public BersenhamCircunferencias_11(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCircle(int xc, int yc, int R, Color color) {
        int x = 0;
        int y = R;
        int d = 3 - (2 * R);

        while (x <= y) {
            if (d <= 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }

            putPixel(xc + x, yc + y, color); // Octante 1
            putPixel(xc + y, yc + x, color); // Octante 2
            putPixel(xc + y, yc - x, color); // Octante 3
            putPixel(xc + x, yc - y, color); // Octante 4
            putPixel(xc - x, yc - y, color); // Octante 5
            putPixel(xc - y, yc - x, color); // Octante 6
            putPixel(xc - y, yc + x, color); // Octante 7
            putPixel(xc - x, yc + y, color); // Octante 8

            x++;
        }
        repaint();
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        BersenhamCircunferencias_11 panel1 = new BersenhamCircunferencias_11(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawCircle(250, 250, 150, Color.BLACK);
    }
}