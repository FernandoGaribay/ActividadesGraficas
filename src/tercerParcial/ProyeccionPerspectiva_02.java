package tercerParcial;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

public class ProyeccionPerspectiva_02 extends JPanel {

    private BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public boolean showLines = true;

    public ProyeccionPerspectiva_02(Color color, int width, int height) {
        setBackground(color);
        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        resetBuffer();
    }

    public void drawCube(int[] coordenadaCubo, int[] coordenadaProyeccion, int size) {
        int xi = coordenadaCubo[0];
        int yi = coordenadaCubo[1];
        int zi = coordenadaCubo[2];

        int Xc = coordenadaProyeccion[0];
        int Yc = coordenadaProyeccion[1];
        int Zc = coordenadaProyeccion[2];

        int[][] vertices = {
            {xi, yi, zi},
            {xi + size, yi, zi},
            {xi + size, yi + size, zi},
            {xi, yi + size, zi},
            {xi, yi, zi + size},
            {xi + size, yi, zi + size},
            {xi + size, yi + size, zi + size},
            {xi, yi + size, zi + size}
        };

        int[][] projectedVertices = new int[8][2];
        for (int i = 0; i < vertices.length; i++) {
            int x1 = vertices[i][0];
            int y1 = vertices[i][1];
            int z1 = vertices[i][2];
            double U = -(Zc / (double) (z1 - Zc));  // Calcula el factor U
            projectedVertices[i][0] = (int) (Xc + (x1 - Xc) * U);
            projectedVertices[i][1] = (int) (Yc + (y1 - Yc) * U);
        }

        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}, {3, 0},
            {4, 5}, {5, 6}, {6, 7}, {7, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };

        for (int[] edge : edges) {
            int x0 = projectedVertices[edge[0]][0];
            int y0 = projectedVertices[edge[0]][1];
            int x1 = projectedVertices[edge[1]][0];
            int y1 = projectedVertices[edge[1]][1];
            drawLine(x0, y0, x1, y1, Color.BLACK);

            if (showLines) {
                drawLine(x0, y0, Xc, Yc, Color.red);
                drawLine(x1, y1, Xc, Yc, Color.red);
            }
        }

        repaint();
    }

    public void drawLine(int x0, int y0, int x1, int y1, Color color) {
        Graphics g = buffer.getGraphics();
        g.setColor(color);
        g.drawLine(x0, y0, x1, y1);
    }

    public void resetBuffer() {
        Graphics g = buffer.getGraphics();
        g.setColor(getBackground());
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        FrameProyeccionPerspectiva_02.createAndShowGUI();
    }
}

class FrameProyeccionPerspectiva_02 {

    private static JFrame frame = createFrame();
    private static ProyeccionPerspectiva_02 panel1 = createBuffer();
    private static int[] coordenadaCubo = {350, 220, 0}; // Coordenadas para centrar el cubo
    private static int[] coordenadaProyeccion = {50, 50, 600}; // Coordenadas para centrar el cubo

    private static JToggleButton btnShowLines;
    private static JToggleButton btnAnimacion;

    public static void createAndShowGUI() {
        frame = createFrame();
        panel1 = createBuffer();

        btnShowLines = new JToggleButton("Mostrar Centro de proyeccion");
        btnShowLines.setSelected(true);
        btnShowLines.setBounds(25, 510, 230, 40);

        btnAnimacion = new JToggleButton("Mostrar animacion");
        btnAnimacion.setBounds(445, 510, 230, 40);

        frame.add(panel1);
        frame.add(btnShowLines);
        frame.add(btnAnimacion);
        frame.setVisible(true);

        addListeners(panel1);
        showCube();
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 560);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static ProyeccionPerspectiva_02 createBuffer() {
        ProyeccionPerspectiva_02 panel1 = new ProyeccionPerspectiva_02(Color.WHITE, 700, 500);
        panel1.setBounds(0, 0, 700, 500);

        return panel1;
    }

    private static void addListeners(ProyeccionPerspectiva_02 panel1) {
        btnShowLines.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Lineas al centro de proyeccion mostradas");
                    panel1.showLines = true;
                } else {
                    System.out.println("Lineas al centro de proyeccion ocultas");
                    panel1.showLines = false;
                }
                showCube();
            }
        });

        btnAnimacion.addItemListener(new ItemListener() {

            private boolean animacionActiva = false;

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    animacionActiva = true;
                    int xCentro = 350;
                    int yCentro = 250;

                    Thread hilo = new Thread(() -> {
                        int angulo = 0;
                        while (animacionActiva) {
                            int newX = (int) (200 * Math.cos(Math.toRadians(angulo)) + xCentro);
                            int newY = (int) (200 * Math.sin(Math.toRadians(angulo)) + yCentro);
                            angulo += 1;
                            
                            coordenadaProyeccion[0] = newX;
                            coordenadaProyeccion[1] = newY;

                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(FrameProyeccionPerspectiva_02.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            showCube();
                        }
                    });
                    hilo.start();
                } else {
                    animacionActiva = false;
                }
            }
        });

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                actualizarCoordenadasProyeccion(e, panel1);
            }
        });

        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                actualizarCoordenadasProyeccion(e, panel1);
            }
        });

        panel1.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                actualizarEjeZProyeccion(e, panel1);
            }
        });
    }

    private static void actualizarCoordenadasProyeccion(MouseEvent e, ProyeccionPerspectiva_02 panel1) {
        int x = e.getX();
        int y = e.getY();
        System.out.println("Click en: " + x + "," + y);
        coordenadaProyeccion[0] = x;
        coordenadaProyeccion[1] = y;
        showCube();
    }

    private static void actualizarEjeZProyeccion(MouseWheelEvent e, ProyeccionPerspectiva_02 panel1) {
        int z = e.getWheelRotation();
        coordenadaProyeccion[2] = (z > 0) ? coordenadaProyeccion[2] - 25 : coordenadaProyeccion[2] + 25;
        System.out.println("Eje Z del punto de proyeccion; " + coordenadaProyeccion[2]);
        showCube();
    }

    private static void showCube() {
        panel1.resetBuffer();
        panel1.drawCube(coordenadaCubo, coordenadaProyeccion, 60);
    }
}
