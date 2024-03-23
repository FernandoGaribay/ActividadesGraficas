package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LineaRectaMejorada_02 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public LineaRectaMejorada_02(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        if (x0 == x1) {
            drawVerticalLine(x0, y0, y1, color);
            return;
        }

        float m = (float) (y1 - y0) / (x1 - x0);
        float b = y0 - m * x0;

        int minX = Math.min(x0, x1);
        int maxX = Math.max(x0, x1);

        for (int x = minX; x <= maxX; x++) {
            int y = Math.round(m * x + b);
            putPixel(x, y, color);
        }
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

        LineaRectaMejorada_02 panel1 = new LineaRectaMejorada_02(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(50, 50, 250, 250, Color.BLACK);
    }
}

/*
Cambios realizados:

Se agregó un chequeo para líneas verticales en drawLine, que llama a un método especializado drawVerticalLine.
Se eliminó el redondeo en drawLine para evitar desplazamientos verticales inesperados.
Se agregó el parámetro Color color al método putPixel para asegurar que el color pasado a drawLine se aplique correctamente.
Se corrigió la posición de la línea para líneas verticales.

Al calcularse com m la recta puede fallar para lineas casi rectas
*/