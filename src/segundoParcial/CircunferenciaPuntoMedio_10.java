package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CircunferenciaPuntoMedio_10 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public CircunferenciaPuntoMedio_10(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCircle(int xc, int yc, float R, Color color) {
        putPixel(0, (int) R, color);
        int x = 0;
        int y = (int) R;
        int p0 = 5 / 4 - (int) R; //si R es entero, p0 = 1 - r

        while (x <= y) {
            if (p0 < 0) {
                p0 += 2 * x + 3;
            } else {
                p0 += 2 * (x - y) + 5;
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

        CircunferenciaPuntoMedio_10 panel1 = new CircunferenciaPuntoMedio_10(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawCircle(250, 250, 150, Color.BLACK);
    }
}
