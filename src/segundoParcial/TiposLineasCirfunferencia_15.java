package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class TiposLineasCirfunferencia_15 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public TiposLineasCirfunferencia_15(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCircle(int xc, int yc, int r, int[] mascara, Color color) {
        int x = 0, y = r;
        int d = 3 - 2 * r;
        int maskIndex = 0;
        while (y >= x) {
            if (mascara[maskIndex % mascara.length] == 1) {
                putPixel(xc + x, yc + y, color);
                putPixel(xc + y, yc + x, color);
                putPixel(xc - x, yc + y, color);
                putPixel(xc - y, yc + x, color);
                putPixel(xc + x, yc - y, color);
                putPixel(xc + y, yc - x, color);
                putPixel(xc - x, yc - y, color);
                putPixel(xc - y, yc - x, color);
            }
            if (d <= 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y = y - 1;
            }
            x++;
            maskIndex++;
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

        TiposLineasCirfunferencia_15 panel1 = new TiposLineasCirfunferencia_15(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        int xc = 500 / 2;
        int yc = 500 / 2;
        int r = 200;

        int[] circunferenciaCompleta = {1};
        int[] mascaraCircunferenciaPunteada = {1, 1, 1, 0, 0, 0};
        int[] circunferenciaTipo = {1, 0, 0, 0};

        panel1.drawCircle(xc, yc, r, circunferenciaCompleta, new Color(5, 5, 5));
        panel1.drawCircle(xc, yc, r - 50, mascaraCircunferenciaPunteada, new Color(204, 0, 0));
        panel1.drawCircle(xc, yc, r - 100, circunferenciaTipo, new Color(0, 153, 51));
    }
}

/*

Deficiencias encontradas:

Duplicacion de pixeles: En el metodo drawCircle, se pinta cada pixel de la circunferencia multiples veces debido al bucle 
    que itera sobre la mascara. Esto puede conducir a una redundancia en la pintura de pixeles y afectar negativamente 
    el rendimiento.

Complejidad de calculo: El algoritmo utilizado para dibujar la circunferencia (algoritmo de Bresenham) es bastante eficiente, 
    pero la manipulacion de la mascara dentro del bucle puede aumentar la complejidad y dificultar el procesamiento de codigo.


*/