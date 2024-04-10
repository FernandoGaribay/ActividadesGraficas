package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PuntoMedio_05 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public PuntoMedio_05(Color color, int width, int height) {
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

    private void drawLine(Color color, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            putPixel(x0, y0, color);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
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

        PuntoMedio_05 panel1 = new PuntoMedio_05(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(Color.BLACK, 50, 50, 100, 400);
    }
}

/*
Algoritmo de Bresenham (Bresenham_04)

Rendimiento matemático:
El algoritmo de Bresenham utiliza operaciones aritméticas simples como sumas y restas, lo que lo hace eficiente en términos de rendimiento matemático, ya que las operaciones de multiplicación son evitadas, lo que es una ventaja en términos de rendimiento en comparación con algoritmos que pueden requerir multiplicaciones.

Espaciado entre píxeles:
Dado que solo hay dos opciones de incremento por cada paso, las líneas dibujadas con este algoritmo tienen un espaciado uniforme entre píxeles en la dirección x e y, lo que las hace visualmente más limpias y precisas.

Velocidad:
El algoritmo de Bresenham es conocido por ser rápido y eficiente, lo que lo hace ideal para aplicaciones en tiempo real donde se necesita dibujar líneas rápidamente.
Debido a su eficiencia en el uso de operaciones básicas y su capacidad para trazar líneas con un espaciado uniforme, es una elección popular para gráficos interactivos y juegos.


Algoritmo de Punto Medio (PuntoMedio_05)

Rendimiento matemático:
El algoritmo de punto medio también utiliza operaciones aritméticas simples como sumas y restas, así como comparaciones básicas (1 multiplicacion).
Aunque es un poco más complejo que el algoritmo de Bresenham, todavía es eficiente en términos de rendimiento matemático.

Espaciado entre píxeles:
Al igual que el algoritmo de Bresenham, las líneas dibujadas con este algoritmo tienen un espaciado uniforme en la dirección x e y, lo que las hace visualmente precisas.

Velocidad:
Aunque sigue siendo eficiente y adecuado para la mayoría de las aplicaciones de gráficos, puede ser menos óptimo en términos de velocidad en comparación con el algoritmo de Bresenham.
*/