import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RecorteLineas_19 extends JFrame {
    
    private BufferedImage buffer;
    public Graphics graPixel;

    // Regiones
    private static final int INSIDE = 0; // 0000
    private static final int LEFT = 1;   // 0001
    private static final int RIGHT = 2;  // 0010
    private static final int BOTTOM = 4; // 0100
    private static final int TOP = 8;    // 1000

    public RecorteLineas_19() {
        setSize(600, 600);
        setTitle("Tarea 19: Recorte de lineas");
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
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    // Función para obtener el código de región de un punto (x, y) con respecto al rectángulo definido por (xLeft, xRight, yTop, yBottom)
    private int computeOutCode(int x, int y, int xLeft, int xRight, int yTop, int yBottom) {
        int code = INSIDE;

        if (x < xLeft)
            code |= LEFT;
        else if (x > xRight)
            code |= RIGHT;

        if (y < yTop)
            code |= TOP;
        else if (y > yBottom)
            code |= BOTTOM;

        return code;
    }

    public void drawBresenhamLine(int x0, int x1, int y0, int y1, int xLeft, int xRight, int yTop, int yBottom) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            int code0 = computeOutCode(x0, y0, xLeft, xRight, yTop, yBottom);
            int code1 = computeOutCode(x1, y1, xLeft, xRight, yTop, yBottom);

            if ((code0 | code1) == 0) { // Ambos puntos están dentro de la ventana
                putPixel(x0, y0, Color.RED);
                if (x0 == x1 && y0 == y1)
                    break;
                int e2 = 2 * err;
                if (e2 > -dy) {
                    err -= dy;
                    x0 += sx;
                }
                if (e2 < dx) {
                    err += dx;
                    y0 += sy;
                }
            } else if ((code0 & code1) != 0) { // Ambos puntos están fuera de la ventana en la misma región
                break;
            } else {
                int codeOut = (code0 != 0) ? code0 : code1;
                int x, y;

                if ((codeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (yTop - y0) / (y1 - y0);
                    y = yTop;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (yBottom - y0) / (y1 - y0);
                    y = yBottom;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (xRight - x0) / (x1 - x0);
                    x = xRight;
                } else {
                    y = y0 + (y1 - y0) * (xLeft - x0) / (x1 - x0);
                    x = xLeft;
                }

                if (codeOut == code0) {
                    x0 = x;
                    y0 = y;
                } else {
                    x1 = x;
                    y1 = y;
                }
            }
        }
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
        RecorteLineas_19 ventana = new RecorteLineas_19();
        ventana.setVisible(true);

        // Coordenadas del rectángulo de recorte
        int xLeft = 100;
        int xRight = 350;
        int yTop = 50;
        int yBottom = 200;

        ventana.drawRectangle(100,50,350,200, Color.black);

        ventana.drawBresenhamLine(50, 380, 50, 300, xLeft, xRight, yTop, yBottom); // Línea 1 Totalmente visible
        ventana.drawBresenhamLine(150, 180, 100, 130, xLeft, xRight, yTop, yBottom); // Línea 2 Parcialmente Visible
        ventana.drawBresenhamLine(50, 50, 100, 200, xLeft, xRight, yTop, yBottom); // Línea 3 Totalmente invisible

        // Lineas de prueba para el rectangulo
        ventana.drawBresenhamLine(120, 120, 0, 400, xLeft, xRight, yTop, yBottom);
        ventana.drawBresenhamLine(50, 400, 300, 0, xLeft, xRight, yTop, yBottom);
        ventana.drawBresenhamLine(0, 400, 150, 150, xLeft, xRight, yTop, yBottom);
    }

}

