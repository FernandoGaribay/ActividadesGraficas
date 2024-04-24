package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GrosorCircunferencias_16 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public GrosorCircunferencias_16(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    private void drawCircle(int xc, int yc, int r, int GROSOR_LINEA, Color color) {
        for (int i = 0; i < GROSOR_LINEA; i++) {
            drawCircle(xc, yc, r + i, color);
        }
    }

    private void drawCircle(int xc, int yc, int r, Color color) {
        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (x <= y) {
            putPixel(xc + x, yc + y, color);
            putPixel(xc - x, yc + y, color);
            putPixel(xc + x, yc - y, color);
            putPixel(xc - x, yc - y, color);
            putPixel(xc + y, yc + x, color);
            putPixel(xc - y, yc + x, color);
            putPixel(xc + y, yc - x, color);
            putPixel(xc - y, yc - x, color);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
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

        GrosorCircunferencias_16 panel1 = new GrosorCircunferencias_16(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        int xc = 500 / 2;
        int yc = 500 / 2;
        int r = 200;

        panel1.drawCircle(xc, yc, r, 3, new Color(0, 0, 0));
    }
}

/*

Deficiencias encontradas:

Duplicacion de pixeles: Aunque este enfoque evita la duplicacion de pixeles al llamar al metodo drawCircle varias veces, aun 
    existe la posibilidad de pintar dos veces el mismo pixel debido a la forma en que se estan calculando los puntos simetricos 
    en la circunferencia. Esto ocurre cuando el grosor de la linea es mayor que 1.

Complejidad de calculo: El algoritmo para dibujar la circunferencia sigue siendo el algoritmo de Bresenham, que es eficiente, 
    pero la complejidad de calculo puede aumentar cuando el grosor de la linea es grande, ya que se deben calcular multiples circulos.

Rotacion de la mascara: En este codigo, no hay rotacion de la mascara, lo que simplifica la logica en comparacion con la practica anterior. 
    Sin embargo, sigue habiendo complejidad en el calculo de multiples circulos para representar el grosor de la linea.

*/