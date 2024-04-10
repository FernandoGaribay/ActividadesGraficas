package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Figuras_12 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public Figuras_12(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        int pasos = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncremento = (float) dx / pasos;
        float yIncremento = (float) dy / pasos;

        float x = x0;
        float y = y0;

        for (int i = 0; i <= pasos; i++) {
            putPixel(Math.round(x), Math.round(y), color);
            x += xIncremento;
            y += yIncremento;
        }

        repaint();
    }

    public void drawRect(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color);
        drawLine(x0, y1, x1, y1, color);
        drawLine(x0, y0, x0, y1, color);
        drawLine(x1, y0, x1, y1, color);
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

            putPixel(xc + x + R, yc + y + R, color); // Octante 1
            putPixel(xc + y + R, yc + x + R, color); // Octante 2
            putPixel(xc + y + R, yc - x + R, color); // Octante 3
            putPixel(xc + x + R, yc - y + R, color); // Octante 4
            putPixel(xc - x + R, yc - y + R, color); // Octante 5
            putPixel(xc - y + R, yc - x + R, color); // Octante 6
            putPixel(xc - y + R, yc + x + R, color); // Octante 7
            putPixel(xc - x + R, yc + y + R, color); // Octante 8

            x++;
        }
        repaint();
    }

    public void drawEllipse(int xc, int yc, int a, int b, Color color) {
        int x = 0;
        int y = b;
        int aSquared = a * a;
        int bSquared = b * b;
        double p0 = bSquared - aSquared * b + 0.25 * aSquared;

        while (2 * bSquared * x < 2 * aSquared * y) {
            putPixel(xc + x + a, yc + y + b, color); // Región 1
            putPixel(xc - x + a, yc + y + b, color); // Región 2
            putPixel(xc - x + a, yc - y + b, color); // Región 3
            putPixel(xc + x + a, yc - y + b, color); // Región 4

            if (p0 < 0) {
                p0 += 2 * bSquared * (x + 1) + bSquared;
            } else {
                p0 += 2 * bSquared * (x + 1) + bSquared - 2 * aSquared * (y - 1);
                y--;
            }
            x++;
        }

        double p1 = bSquared * (x + 0.5) * (x + 0.5) + aSquared * (y - 1) * (y - 1) - aSquared * bSquared;

        while (y >= 0) {
            putPixel(xc + x + a, yc + y + b, color); // Región 1
            putPixel(xc - x + a, yc + y + b, color); // Región 2
            putPixel(xc - x + a, yc - y + b, color); // Región 3
            putPixel(xc + x + a, yc - y + b, color); // Región 4

            if (p1 > 0) {
                p1 += -2 * aSquared * (y - 1) + aSquared;
            } else {
                p1 += -2 * aSquared * (y - 1) + aSquared + 2 * bSquared * (x + 1);
                x++;
            }
            y--;
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
        frame.setSize(525, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        Figuras_12 panel1 = new Figuras_12(Color.WHITE, 525, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(30, 30, 130, 130, Color.BLACK);
        panel1.drawLine(140, 65, 240, 65, Color.BLACK);
        panel1.drawLine(355, 30, 255, 130, Color.BLACK);
        panel1.drawLine(495, 65, 386, 65, Color.BLACK);

        panel1.drawCircle(30, 250, 50, Color.BLACK);
        panel1.drawCircle(45, 265, 35, Color.BLACK);
        panel1.drawCircle(60, 280, 20, Color.BLACK);
        panel1.drawCircle(75, 295, 5, Color.BLACK);

        panel1.drawRect(150, 250, 280, 320, Color.BLACK);
        panel1.drawRect(260, 300, 170, 270, Color.BLACK);

        panel1.drawEllipse(300, 250, 100, 50, Color.BLACK);
        panel1.drawEllipse(320, 260, 80, 40, Color.BLACK);
        panel1.drawEllipse(340, 275, 60, 25, Color.BLACK);
        panel1.drawEllipse(360, 290, 40, 10, Color.BLACK);
    }
}
