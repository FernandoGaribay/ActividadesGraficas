package segundoParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ScanLine_17 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public ScanLine_17(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void fillRectangle(int x0, int y0, int x1, int y1, Color color) {
        int nx0 = Math.min(x0, x1);
        int ny0 = Math.min(y0, y1);
        int nx1 = Math.max(x0, x1);
        int ny1 = Math.max(y0, y1);
        
        for (int y = ny0; y <= ny1; y++) {
            int xInicio = -1;
            for (int x = nx0; x <= nx1; x++) {
                if (xInicio == -1) {
                    xInicio = x;
                }
                if (xInicio != -1) {
                    putPixel(xInicio, y, color);
                    xInicio = -1;
                }
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

        ScanLine_17 panel1 = new ScanLine_17(Color.WHITE, 500, 500);
        panel1.setLocation(0, 0);
        frame.add(panel1);

        frame.setVisible(true);

        panel1.fillRectangle(10, 10, 150, 150, Color.BLACK);
    }
}
