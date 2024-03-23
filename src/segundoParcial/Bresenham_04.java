package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Bresenham_04 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public Bresenham_04(Color color, int width, int height) {
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

    private void drawLine(Color color, int x1, int y1, int x2, int y2) {
        int incyi, incxi, incyr, incxr, aux, avr, av, avi;
        int dx = x2 - x1;
        int dy = y2 - y1;

        if (dy >= 0) {
            incyi = 1;
        } else {
            dy = -dy;
            incyi = -1;
        }
        
        if(dx >= 0){
            incxi = 1;
        } else {
            dx = -dx;
            incxi = -1;
        }
        
        if(dx >= dy){
            incyr = 0;
            incxr = incxi;
        } else {
            incxr = 0;
            incyr = incyi;
            aux = dx;
            dx = dy;
            dy = aux;
        }
        
        int x = x1;
        int y = y1;
        avr = 2 * dy;
        av = avr - dx;
        avi = av - dx;
        do {
            putPixel(x, y, color);
            if(av >= 0){
                x = x + incxi;
                y = y + incyi;
                av = av + avi;
            } else {
                x = x + incxr;
                y = y + incyr;
                av = av + avr;
            }
        }while(x != x2);

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

        Bresenham_04 panel1 = new Bresenham_04(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.drawLine(Color.BLACK, 50, 50, 100, 300);
    }
}
