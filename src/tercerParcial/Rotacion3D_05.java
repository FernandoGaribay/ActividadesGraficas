package tercerParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Rotacion3D_05 extends JPanel implements KeyListener {

    GraficosRotacion3D_05 buffer = new GraficosRotacion3D_05(800, 800);

    private double[] puntoCubo = {400, 400, 0};
    private double[] puntoFuga = {400, 400, 500};

    private double traslacionX = 0;
    private double traslacionY = 0;
    private double traslacionZ = 0;

    private double anguloX = 0;
    private double anguloY = 0;
    private double anguloZ = 0;

    private int escala = 60;

    private double[][] vertices = {
        {-1, -1, -1},
        {1, -1, -1},
        {1, 1, -1},
        {-1, 1, -1},
        {-1, -1, 1},
        {1, -1, 1},
        {1, 1, 1},
        {-1, 1, 1}
    };

    private int[][] edges = {
        {0, 1}, {1, 2}, {2, 3}, {3, 0},
        {4, 5}, {5, 6}, {6, 7}, {7, 4},
        {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };

    public Rotacion3D_05() {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        double[][] verticesTrasladados = new double[8][3];
        for (int i = 0; i < vertices.length; i++) {
            double[] vertice = vertices[i];
            vertice = rotarX(vertice, anguloX);
            vertice = rotarY(vertice, anguloY);
            vertice = rotarZ(vertice, anguloZ);
            verticesTrasladados[i] = vertice;
        }

        for (int i = 0; i < vertices.length; i++) {
            double[] v = verticesTrasladados[i];
            double[] trasladado = {
                (v[0] * escala) + traslacionX + puntoCubo[0],
                (v[1] * escala) + traslacionY + puntoCubo[1],
                (v[2] * escala) + traslacionZ + puntoCubo[2]
            };
            verticesTrasladados[i] = trasladado;
        }

        for (int[] edge : edges) {
            double x0 = verticesTrasladados[edge[0]][0];
            double y0 = verticesTrasladados[edge[0]][1];
            double z0 = verticesTrasladados[edge[0]][2];

            double x1 = verticesTrasladados[edge[1]][0];
            double y1 = verticesTrasladados[edge[1]][1];
            double z1 = verticesTrasladados[edge[1]][2];

            Point2D p1 = punto3D_a_2D(x0, y0, z0);
            Point2D p2 = punto3D_a_2D(x1, y1, z1);

            buffer.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), Color.BLACK);
//            buffer.drawLine((int) p1.getX(), (int) p1.getY(), (int) puntoFuga[0], (int) puntoFuga[1], Color.RED);
        }
        g.drawImage(buffer.getBuffer(), 0, 0, null);
        buffer.resetBuffer();
    }

    private Point2D punto3D_a_2D(double x, double y, double z) {
        double u = -puntoFuga[2] / (z - puntoFuga[2]);

        double px = puntoFuga[0] + (x - puntoFuga[0]) * u;
        double py = puntoFuga[1] + (y - puntoFuga[1]) * u;

        return new Point2D.Double(px, py);
    }

    private double[] rotarX(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0];
        result[1] = point[1] * Math.cos(Math.toRadians(angle)) - point[2] * Math.sin(Math.toRadians(angle));
        result[2] = point[1] * Math.sin(Math.toRadians(angle)) + point[2] * Math.cos(Math.toRadians(angle));
        return result;
    }

    private double[] rotarY(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0] * Math.cos(Math.toRadians(angle)) + point[2] * Math.sin(Math.toRadians(angle));
        result[1] = point[1];
        result[2] = -point[0] * Math.sin(Math.toRadians(angle)) + point[2] * Math.cos(Math.toRadians(angle));
        return result;
    }

    private double[] rotarZ(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0] * Math.cos(Math.toRadians(angle)) - point[1] * Math.sin(Math.toRadians(angle));
        result[1] = point[0] * Math.sin(Math.toRadians(angle)) + point[1] * Math.cos(Math.toRadians(angle));
        result[2] = point[2];
        return result;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A ->
                traslacionX -= 10;
            case KeyEvent.VK_D ->
                traslacionX += 10;
            case KeyEvent.VK_W ->
                traslacionY -= 10;
            case KeyEvent.VK_S ->
                traslacionY += 10;
            case KeyEvent.VK_UP ->
                traslacionZ += 10;
            case KeyEvent.VK_DOWN ->
                traslacionZ -= 10;
            case KeyEvent.VK_LEFT ->
                escala -= 1;
            case KeyEvent.VK_RIGHT ->
                escala += 1;
            case KeyEvent.VK_I ->
                anguloX += 5;
            case KeyEvent.VK_K ->
                anguloX -= 5;
            case KeyEvent.VK_J ->
                anguloY -= 5;
            case KeyEvent.VK_L ->
                anguloY += 5;
            case KeyEvent.VK_U ->
                anguloZ -= 5;
            case KeyEvent.VK_O ->
                anguloZ += 5;
            default -> {
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Rotacion3D_05::new);
    }
}

class GraficosRotacion3D_05 {

    private BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public GraficosRotacion3D_05(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;

        int pasos = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncremento = (float) dx / pasos;
        float yIncremento = (float) dy / pasos;

        float x = x0;
        float y = y0;

        for (int i = 0; i <= pasos; i++) {
            putPixel(Math.round(x), Math.round(y), color);
            x += xIncremento;
            y += yIncremento;
        }
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    public void resetBuffer() {
        buffer = new BufferedImage(HEIGHT, WIDTH, BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }
}
