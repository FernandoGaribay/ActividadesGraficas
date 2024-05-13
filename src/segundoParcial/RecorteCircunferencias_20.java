import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RecorteCircunferencias_20 extends JFrame {
    private BufferedImage buffer;
    public Graphics graPixel;

    // Definición de códigos de región
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    private static final int xLeft = 100;
    private static final int xRight = 350;
    private static final int yTop = 50;
    private static final int yBottom = 200;

    public RecorteCircunferencias_20() {
        setSize(600, 600);
        setTitle("Recorte de circunferencias");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        graPixel = buffer.createGraphics();
        getContentPane().setBackground(Color.WHITE);
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(x, y, c.getRGB());
        this.getGraphics().drawImage(buffer, 0, 0, this);
        repaint();

    }
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public void drawBresenhamCircumference(int centerX, int centerY, int radius) {
        int x = 0;
        int y = radius;
        int p = 3 - 2 * radius;

        drawCirclePoints(centerX, centerY, x, y);

        while (x <= y) {
            x++;
            if (p < 0) {
                p += 4 * x + 6;
            } else {
                y--;
                p += 4 * (x - y) + 10;
            }
            drawCirclePoints(centerX, centerY, x, y);
        }
    }

    private void drawCirclePoints(int centerX, int centerY, int x, int y) {
        int code = calculateRegionCode(centerX + x, centerY + y);
        if (code == INSIDE) {
            putPixel(centerX + x, centerY + y, Color.BLUE);
        }
        code = calculateRegionCode(centerX - x, centerY + y);
        if (code == INSIDE) {
            putPixel(centerX - x, centerY + y, Color.BLUE);
        }
        code = calculateRegionCode(centerX + x, centerY - y);
        if (code == INSIDE) {
            putPixel(centerX + x, centerY - y, Color.BLUE);
        }
        code = calculateRegionCode(centerX - x, centerY - y);
        if (code == INSIDE) {
            putPixel(centerX - x, centerY - y, Color.BLUE);
        }
        code = calculateRegionCode(centerX + y, centerY + x);
        if (code == INSIDE) {
            putPixel(centerX + y, centerY + x, Color.BLUE);
        }
        code = calculateRegionCode(centerX - y, centerY + x);
        if (code == INSIDE) {
            putPixel(centerX - y, centerY + x, Color.BLUE);
        }
        code = calculateRegionCode(centerX + y, centerY - x);
        if (code == INSIDE) {
            putPixel(centerX + y, centerY - x, Color.BLUE);
        }
        code = calculateRegionCode(centerX - y, centerY - x);
        if (code == INSIDE) {
            putPixel(centerX - y, centerY - x, Color.BLUE);
        }
    }

    private int calculateRegionCode(int x, int y) {
        int code = INSIDE;
        if (x < xLeft) {
            code |= LEFT;
        } else if (x > xRight) {
            code |= RIGHT;
        }
        if (y < yTop) {
            code |= TOP;
        } else if (y > yBottom) {
            code |= BOTTOM;
        }
        return code;
    }

    public void drawRectangle(int x0, int y0, int x1, int y1, Color color) {
        drawLine(x0, y0, x1, y0, color); // Línea superior
        drawLine(x0, y0, x0, y1, color); // Línea izquierda
        drawLine(x1, y0, x1, y1, color); // Línea derecha
        drawLine(x0, y1, x1, y1, color); // Línea inferior
    }
    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        if (x0 == x1) 
        { // Si la línea es vertical
            drawVerticalLine(x0, y0, y1, color);
        }
        else 
        {
            drawNonVerticalLineBresenham(x0, y0, x1, y1, color);
        }
    }

    public void drawNonVerticalLineBresenham(int x0, int y0, int x1, int y1, Color color) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;
        int e2;
    
        while (true) {
            putPixel(x0, y0, color);
    
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
        }
    }

    private void drawVerticalLine(int x, int startY, int endY, Color color) {
        int minY = Math.min(startY, endY);
        int maxY = Math.max(startY, endY);
        for (int y = minY; y <= maxY; y++) {
            putPixel(x, y, color);
        }
    }


    public static void main(String[] args) {
        RecorteCircunferencias_20 ventana = new RecorteCircunferencias_20();
        ventana.setVisible(true);
        ventana.setBackground(Color.white);

        ventana.drawRectangle(100,50,350,200, Color.BLACK);
        ventana.drawBresenhamCircumference(200, 200, 100);
        ventana.drawBresenhamCircumference(100, 100, 50);
        ventana.drawBresenhamCircumference(350, 200, 150);
        ventana.drawBresenhamCircumference(270, 150, 30);
    }
}


