package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CoordenadasPolares_08 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public CoordenadasPolares_08(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCircle(int xc, int yc, int R, Color color) {
        for (int t = 0; t <= 360; t++) {
            int x = (int) (xc + R * Math.sin(Math.toRadians(t)));
            int y = (int) (yc + R * Math.cos(Math.toRadians(t)));
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

        CoordenadasPolares_08 panel1 = new CoordenadasPolares_08(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawCircle(250, 250, 150, Color.BLACK);
    }
}

/*
Complejidad:
La forma en que se está dibujando el círculo utilizando coordenadas polares en un bucle de 0 a 360 grados es ineficiente y no garantiza que cada píxel esté llenado adecuadamente.
Esto puede llevar a agujeros en el círculo y no es una representación precisa de un círculo, especialmente para radios grandes.
La complejidad es alta debido al bucle de 0 a 360 grados, lo que puede llevar a un rendimiento subóptimo para círculos grandes.

Precisión y redondeo:
La conversión de las coordenadas calculadas a enteros puede resultar en pérdida de precisión y en agujeros en el círculo.
Los valores de seno y coseno no son exactos, y al redondear a enteros, los píxeles pueden quedar sin llenar o se pueden dibujar píxeles incorrectos.
*/