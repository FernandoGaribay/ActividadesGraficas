package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimetriaOchoLados_09 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public SimetriaOchoLados_09(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCircle(int xc, int yc, int R, Color color) {
        for (int t = 0; t <= 45; t++) {
            int x = (int) (R * Math.sin(Math.toRadians(t)));
            int y = (int) (R * Math.cos(Math.toRadians(t)));

            putPixel(xc + x, yc + y, color); 
            putPixel(xc + y, yc + x, color);
            putPixel(xc + y, yc - x, color); 
            putPixel(xc + x, yc - y, color); 

            putPixel(xc - x, yc - y, color); 
            putPixel(xc - y, yc - x, color); 
            putPixel(xc - y, yc + x, color);  
            putPixel(xc - x, yc + y, color); 
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

        SimetriaOchoLados_09 panel1 = new SimetriaOchoLados_09(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawCircle(250, 250, 150, Color.BLACK);
    }
}