package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class LineaRecta_01 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public LineaRecta_01(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        float m = (float) ((y1 - y0)) / (x1 - x0);
        float b = y0 - m * x0;

        int nx0 = Math.min(x0, x1);
        int nx1 = Math.max(x0, x1);

        for (int x = nx0; x <= nx1; x++) {
            int y = (int) (Math.round(m * x + b));
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

        LineaRecta_01 panel1 = new LineaRecta_01(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(50, 50, 300, 250, Color.BLACK);
    }
}

/*

Division entre 0: El cálculo de la pendiente (m) es riesgoso ya que podría haber una división por cero si x1 - x0 es igual a 0. Es importante manejar este caso de dibujar una linea recta para evitar errores.
Redondeo inapropiado: Debido al redondeo en int y = (int) (Math.round(m * x + b));, la precisión de los puntos dibujados puede no ser ideal en algunas situaciones. Esto puede llevar a líneas que no se ven perfectamente rectas en ciertos ángulos.
Posición de la línea: La línea se dibuja utilizando el algoritmo de la pendiente, lo que puede producir resultados no deseados para líneas verticales o casi verticales (entre mas cerca esten de los 90 grados).
La ecuación y=mx+b no está directamente enmarcada en la matriz discreta de píxeles, que está compuesta por valores enteros. En un contexto de dibujo de líneas en una pantalla, como se trabaja con píxeles individuales, necesitas encontrar una manera de mapear los puntos de la línea a píxeles específicos en la matriz de píxeles.
*/
