package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GrosorLineaRecta_14 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public GrosorLineaRecta_14(Color color, int width, int height) {
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

        putPixel(Math.round(x), Math.round(y), color);
        for (int i = 0; i <= pasos; i++) {
            x += xIncremento;
            y += yIncremento;
            putPixel(Math.round(x), Math.round(y), color);
        }

        repaint();
    }

    private void drawLine(int x0, int y0, int x1, int y1, int grosor, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        // Verificar si la línea es más vertical que horizontal
        boolean vertical = dy > dx;

        if (vertical) {
            // Dibujar líneas horizontales para cada posición de x
            for (int i = -grosor / 2; i <= grosor / 2; i++) {
                drawLine(x0, y0 + i, x1, y1 + i, color);
            }
        } else {
            // Dibujar líneas verticales para cada posición de y
            for (int i = -grosor / 2; i <= grosor / 2; i++) {
                drawLine(x0 + i, y0, x1 + i, y1, color);
            }
        }
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

        GrosorLineaRecta_14 panel1 = new GrosorLineaRecta_14(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(10, 10, 250, 250, 7, Color.BLACK);
    }
}
