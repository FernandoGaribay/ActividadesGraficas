package tercerParcial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProyeccionPerspectiva_02 extends JPanel {

    // VARIABLES --------------------------------------------------------------
    private BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    private double[] angulos = {0, 0};
    private double[] puntoCubo = {500, 250, 0};
    private double[] puntoFuga = {50, 50, 600};

    private final double escala = 50;

    private final double[][] vertices = {
        {-1, -1, -1},
        {1, -1, -1},
        {1, 1, -1},
        {-1, 1, -1},
        {-1, -1, 1},
        {1, -1, 1},
        {1, 1, 1},
        {-1, 1, 1}
    };
    private final int[][] edges = {
        {0, 1}, {1, 2}, {2, 3}, {3, 0},
        {4, 5}, {5, 6}, {6, 7}, {7, 4},
        {0, 4}, {1, 5}, {2, 6}, {3, 7}
    };

    public ProyeccionPerspectiva_02(int HEIGHT, int WIDTH) {
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
        
        resetBuffer();
        drawCube3D(vertices);
    }

    private Point2D punto3D_a_2D(double x, double y, double z, double[] puntoFuga) {
        double u = -puntoFuga[2] / ((z + puntoCubo[2]) - puntoFuga[2]);

        // Con punto de fuga
        double px = puntoFuga[0] + (x - puntoFuga[0]) * u + puntoCubo[0];
        double py = puntoFuga[1] + (y - puntoFuga[1]) * u + puntoCubo[1];

        return new Point2D.Double(px, py);
    }

    private Point2D punto3D_a_2D(double x, double y, double z) {
        double fov = 500;
        double u = -fov / (z - fov);

        // Sin punto de fuga
        double px = (x * u) + getWidth() / 2;
        double py = (y * u) + getHeight() / 2;

        return new Point2D.Double(px, py);
    }

    private double[] rotarX(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0];
        result[1] = point[1] * Math.cos(angle) - point[2] * Math.sin(angle);
        result[2] = point[1] * Math.sin(angle) + point[2] * Math.cos(angle);
        return result;
    }

    private double[] rotarY(double[] point, double angle) {
        double[] result = new double[3];
        result[0] = point[0] * Math.cos(angle) + point[2] * Math.sin(angle);
        result[1] = point[1];
        result[2] = -point[0] * Math.sin(angle) + point[2] * Math.cos(angle);
        return result;
    }

    public double[][] rotarVertices() {
        double[][] verticesRotados = new double[8][3];
        for (int i = 0; i < vertices.length; i++) {
            double[] vertice = vertices[i];
            vertice = rotarX(vertice, angulos[0]);
            vertice = rotarY(vertice, angulos[1]);
            verticesRotados[i] = vertice;
        }
        return verticesRotados;
    }

    public void drawCube3D(double[][] vertices) {
        resetBuffer();
        for (int[] edge : edges) {
            double x0 = vertices[edge[0]][0] * escala;
            double y0 = vertices[edge[0]][1] * escala;
            double z0 = vertices[edge[0]][2] * escala;

            double x1 = vertices[edge[1]][0] * escala;
            double y1 = vertices[edge[1]][1] * escala;
            double z1 = vertices[edge[1]][2] * escala;

            Point2D p1 = punto3D_a_2D(x0, y0, z0, puntoFuga);
            Point2D p2 = punto3D_a_2D(x1, y1, z1, puntoFuga);

            drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY(), Color.BLACK);
            drawLine((int) p1.getX(), (int) p1.getY(), (int) puntoFuga[0], (int) puntoFuga[1], Color.RED);
        }
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

        repaint();
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    public void resetBuffer() {
        buffer = new BufferedImage(HEIGHT, WIDTH, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public void setAngulos(double[] angulos) {
        this.angulos = angulos;
    }

    public double[] getAngulos() {
        return this.angulos;
    }

    public double[] getPuntoCubo() {
        return puntoCubo;
    }

    public void setPuntoCubo(double[] puntoCubo) {
        this.puntoCubo = puntoCubo;
    }

    public double[] getPuntoFuga() {
        return puntoFuga;
    }

    public void setPuntoFuga(double[] puntoFuga) {
        this.puntoFuga = puntoFuga;
    }
}

class ProyeccionPerspectivaFrame implements MouseMotionListener, MouseListener {

    // VARIABLES --------------------------------------------------------------
    private JFrame frame = new JFrame();
    private ProyeccionPerspectiva_02 panelCubo = new ProyeccionPerspectiva_02(800, 500);

    private JTextArea coordenadasPuntoFuga = new JTextArea();
    private JTextArea coordenadasPuntoCubo = new JTextArea();
    private JSlider puntoFugaZ = new JSlider(0, 1000, 600);
    private JSlider puntoCuboZ = new JSlider(0, 1000, 0);

    private double[] angulos = {0, 0};
    private double[] puntoCubo = {500, 250, 0};
    private double[] puntoFuga = {50, 50, 600};

    private final double escala = 50;
    private int lastMouseX, lastMouseY;

    public ProyeccionPerspectivaFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rotating Cube");
        frame.setSize(1200, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addMouseMotionListener(this);

        actualizarControles();
        inicializarEventos();

        frame.setVisible(true);
    }

    // MAIN --------------------------------------------------------------------
    public static void main(String[] args) {
        new ProyeccionPerspectivaFrame();
    }

    public void actualizarControles() {
        panelCubo.setBounds(0, 0, 800, 500);

        coordenadasPuntoFuga.setText("Punto de Fuga: "
                + "\nx = " + puntoFuga[0]
                + "\ny = " + puntoFuga[1]
                + "\nz = " + puntoFuga[2]);
        coordenadasPuntoFuga.setEditable(false);
        coordenadasPuntoFuga.setFocusable(false);
        coordenadasPuntoFuga.setBackground(new Color(238, 238, 238));
        coordenadasPuntoFuga.setBounds(825, 20, 100, 70);

        coordenadasPuntoCubo.setText("Punto de Cubo: "
                + "\nx = " + puntoCubo[0]
                + "\ny = " + puntoCubo[1]
                + "\nz = " + puntoCubo[2]);
        coordenadasPuntoCubo.setEditable(false);
        coordenadasPuntoCubo.setFocusable(false);
        coordenadasPuntoCubo.setBackground(new Color(238, 238, 238));
        coordenadasPuntoCubo.setBounds(1075, 20, 350, 70);

        puntoFugaZ.setPaintTicks(true);
        puntoFugaZ.setPaintLabels(true);
        puntoFugaZ.setMajorTickSpacing(200);
        puntoFugaZ.setMinorTickSpacing(100);
        puntoFugaZ.setBounds(825, 120, 350, 50);

        puntoCuboZ.setPaintTicks(true);
        puntoCuboZ.setPaintLabels(true);
        puntoCuboZ.setMajorTickSpacing(200);
        puntoCuboZ.setMinorTickSpacing(100);
        puntoCuboZ.setBounds(825, 195, 350, 50);

        frame.add(panelCubo);
        frame.add(coordenadasPuntoFuga);
        frame.add(coordenadasPuntoCubo);
        frame.add(puntoFugaZ);
        frame.add(puntoCuboZ);
    }

    public void inicializarEventos() {
        puntoFugaZ.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                puntoFuga[2] = (int) puntoFugaZ.getValue();
                System.out.println("Eje Z en Punto de Fuja: " + puntoFugaZ.getValue());
                panelCubo.setPuntoFuga(puntoFuga);
                panelCubo.drawCube3D(panelCubo.rotarVertices());
                actualizarControles();
            }
        });

        puntoCuboZ.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                puntoCubo[2] = (int) puntoCuboZ.getValue();
                System.out.println("Eje Z en Punto de Cubo: " + puntoCuboZ.getValue());
                panelCubo.setPuntoCubo(puntoCubo);
                panelCubo.drawCube3D(panelCubo.rotarVertices());
                actualizarControles();
            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int dx = x - lastMouseX;
        int dy = y - lastMouseY;
        lastMouseX = x;
        lastMouseY = y;

        if ((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) != 0) {
            angulos[1] += dx * 0.01;
            angulos[0] -= dy * 0.01;
            panelCubo.setAngulos(angulos);
        } else if ((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) != 0) {
            puntoFuga[0] = x;
            puntoFuga[1] = y;
            panelCubo.setPuntoFuga(puntoFuga);
        }
        panelCubo.drawCube3D(panelCubo.rotarVertices());
        actualizarControles();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent me) {
        lastMouseX = me.getX();
        lastMouseY = me.getY();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
