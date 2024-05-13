package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ScanLine_17 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    private static int posX;
    private static int posY;
    private static int size;

    public ScanLine_17(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public void fillRectangle(int posX, int posY, int size, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;

        drawLine(posX, posY, posX + size, posY, color);
        drawLine(posX + size, posY, posX + size, posY + size, color);
        drawLine(posX + size, posY + size, posX, posY + size, color);
        drawLine(posX, posY + size, posX, posY, color);

        scanLineFill(color);
        repaint();
    }

    private void scanLineFill(Color color) {
        for (int y = posY; y < posY + size; y++) {
            scanLine(y, color);
        }
    }

    private void scanLine(int y, Color color) {
        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            int x0 = posX + ((i + 3) % 4 == 3 ? 0 : size); // coordenada x del punto inicial del borde
            int y0 = posY + ((i + 2) % 4 > 0 ? size : 0); // coordenada y del punto inicial del borde
            int x3 = posX + ((i + 2) % 4 == 2 ? size : 0); // coordenada x del punto final del borde
            int y3 = posY + ((i + 1) % 4 > 0 ? size : 0); // coordenada y del punto final del borde

            if ((y >= y0 && y < y3) || (y >= y3 && y < y0)) {
                int x = (int) (x0 + (double) (y - y0) / (y3 - y0) * (x3 - x0));

                if (x < x1) {
                    x1 = x;
                }
                if (x > x2) {
                    x2 = x;
                }
            }
        }

        if (x1 <= x2) {
            for (int x = x1; x <= x2; x++) {
                putPixel(x, y, color);
            }
        }
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (true) {
            putPixel(x0, y0, color);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        ScanLine_17 panel1 = new ScanLine_17(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.fillRectangle(150, 150, 200, Color.BLACK);
    }
}
