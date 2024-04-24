package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TiposLineasRectas_13 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public TiposLineasRectas_13(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    private void drawLine(int x0, int y0, int x1, int y1, int[] mascara, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        int err = dx - dy;
        int e2;

        int contador = 0; // Contador para la máscara

        while (true) {
            // Dibujar pixel si el bit de la máscara está activo
            if (mascara[contador % mascara.length] == 1) {
                putPixel(x0, y0, color);
            }

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

            contador++;
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

        TiposLineasRectas_13 panel1 = new TiposLineasRectas_13(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        int[] mascaraContinua = {1}; // Línea continua
        int[] mascaraDiscontinua = {1, 0}; // Línea discontinua
        int[] mascaraTipo = {1, 1, 0, 0}; // Tipo de línea

        panel1.drawLine(15, 15, 250, 250, mascaraContinua, Color.BLACK);
        panel1.drawLine(15, 65, 250, 300, mascaraDiscontinua, Color.BLACK);
        panel1.drawLine(15, 125, 250, 350, mascaraTipo, Color.BLACK);
    }
}
