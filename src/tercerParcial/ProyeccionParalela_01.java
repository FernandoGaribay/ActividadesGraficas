package tercerParcial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProyeccionParalela_01 extends JPanel {

    BufferedImage buffer;
    private int WIDTH;
    private int HEIGHT;

    public ProyeccionParalela_01(Color color, int width, int height) {
        setBackground(color);
        setSize(width, height);

        this.WIDTH = width;
        this.HEIGHT = height;
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void drawCube(int[] punto, int zPlano, int size) {
        int x = punto[0];
        int y = punto[1];
        int z = punto[2];

        int[][] vertices = {
            {x, y, z},
            {x + size, y, z},
            {x + size, y + size, z},
            {x, y + size, z},
            {x, y, z + size},
            {x + size, y, z + size},
            {x + size, y + size, z + size},
            {x, y + size, z + size}
        };

        int[][] projectedVertices = new int[8][2];
        int xp = 1;  // Factor de proyección en X
        int yp = 1;  // Factor de proyección en Y
        int zp = zPlano;  // Profundidad del factor de proyección

        for (int i = 0; i < vertices.length; i++) {
            int x1 = vertices[i][0];
            int y1 = vertices[i][1];
            int z1 = vertices[i][2];
            double u = -(z1 / (double) zp);  // Calcula el factor u
            projectedVertices[i][0] = (int) (x1 + xp * u);
            projectedVertices[i][1] = (int) (y1 + yp * u);
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
            drawLine(x0 + WIDTH / 2, y0 + HEIGHT / 2, x1 + WIDTH / 2, y1 + HEIGHT / 2, Color.BLACK);
        }

        repaint();
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
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        FrameProyeccionParalela_01.createAndShowGUI();
    }
}

class FrameProyeccionParalela_01 {

    static JLabel label1 = createJLabel(25, 490, 300, 50, "Punto inicial Coordenada Z: 0");
    static JLabel label2 = createJLabel(375, 490, 300, 50, "Profundidad del factor de proyección Z: 2");
    static JSlider slider1 = createSlider(-500, 500, 0, 200, 100, 25, 530, 300, 50);
    static JSlider slider2 = createSlider(-50, 50, 2, 10, 10, 375, 530, 300, 50);

    public static void createAndShowGUI() {
        JFrame frame = createFrame();
        ProyeccionParalela_01 panel1 = createBuffer();

        addListeners(panel1, slider1, slider2);

        frame.add(panel1);
        frame.add(slider1);
        frame.add(slider2);
        frame.add(label1);
        frame.add(label2);
        frame.setVisible(true);
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700, 580);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static ProyeccionParalela_01 createBuffer() {
        return new ProyeccionParalela_01(Color.WHITE, 700, 500);
    }

    private static JSlider createSlider(int min, int max, int initial, int majorSickSpacing, int minorTickSpacing, int x, int y, int width, int height) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setPaintTicks(true);
        slider.setValue(initial);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(majorSickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);
        slider.setBounds(x, y, width, height);
        return slider;
    }

    private static JLabel createJLabel(int x, int y, int width, int height, String name) {
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        return label;
    }

    private static void addListeners(ProyeccionParalela_01 panel1, JSlider slider1, JSlider slider2) {
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label1.setText("Punto inicial Coordenada Z: " + slider1.getValue());
                updatePanel(panel1, slider1, slider2);
            }
        });

        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                label2.setText("Profundidad del factor de proyección Z: " + slider2.getValue());
                updatePanel(panel1, slider1, slider2);
            }
        });
    }

    private static void updatePanel(ProyeccionParalela_01 panel1, JSlider slider1, JSlider slider2) {
        int[] coordenada = {0, 0, slider1.getValue()};
        int zPlano = slider2.getValue();
        panel1.resetBuffer();
        panel1.drawCube(coordenada, zPlano, 100);
    }
}
