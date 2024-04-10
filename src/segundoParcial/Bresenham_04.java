package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Bresenham_04 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public Bresenham_04(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int incyi, incxi, incyr, incxr, aux, avr, av, avi;
        int dx = x1 - x0;
        int dy = y1 - y0;

        if (x0 == x1) {
            drawVerticalLine(x0, y0, y1, color);
            return;
        }
        if (y0 == y1) {
            drawHorizontalLine(y0, x0, x1, color);
            return;
        }

        if (dy >= 0) {
            incyi = 1;
        } else {
            dy = -dy;
            incyi = -1;
        }

        if (dx >= 0) {
            incxi = 1;
        } else {
            dx = -dx;
            incxi = -1;
        }

        if (dx >= dy) {
            incyr = 0;
            incxr = incxi;
        } else {
            incxr = 0;
            incyr = incyi;
            aux = dx;
            dx = dy;
            dy = aux;
        }

        int x = x0;
        int y = y0;
        avr = 2 * dy;
        av = avr - dx;
        avi = av - dx;
        do {
            putPixel(x, y, color);
            if (av >= 0) {
                x = x + incxi;
                y = y + incyi;
                av = av + avi;
            } else {
                x = x + incxr;
                y = y + incyr;
                av = av + avr;
            }
        } while (x != x1);

        repaint();
    }

    private void drawVerticalLine(int x, int y0, int y1, Color color) {
        int minY = Math.min(y0, y1);
        int maxY = Math.max(y0, y1);

        for (int y = minY; y <= maxY; y++) {
            putPixel(x, y, color);
        }
        repaint();
    }

    private void drawHorizontalLine(int y, int x0, int x1, Color color) {
        int minY = Math.min(x0, x1);
        int maxY = Math.max(x0, x1);

        for (int x = minY; x <= maxY; x++) {
            putPixel(x, y, color);
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

        Bresenham_04 panel1 = new Bresenham_04(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(50, 50, 300, 300, Color.BLACK);
    }
}
