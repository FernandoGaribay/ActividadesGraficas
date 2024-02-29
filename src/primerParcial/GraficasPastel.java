package primerParcial;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraficasPastel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int RADIO;
    private final Color[] COLORES;

    private final int[] porcentajes;
    private final int x;
    private final int y;

    public GraficasPastel(int[] porcentajes) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.porcentajes = porcentajes;
        this.RADIO = (int) (WIDTH * 0.4);
        this.x = WIDTH / 2;
        this.y = HEIGHT / 2;
        this.COLORES = new Color[]{
            Color.BLUE,
            Color.GREEN,
            Color.RED,
            Color.ORANGE,
            Color.PINK,
            Color.MAGENTA
        };
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ultimoAngulo;
        int total = 0;
        for (int porcentaje : porcentajes) {
            total += porcentaje;
        }

        int[] angulos = new int[porcentajes.length];
        for (int i = 0; i < porcentajes.length; i++) {
            angulos[i] = (int) (porcentajes[i] * 360 / total);
        }

        ultimoAngulo = 0;
        for (int i = 0; i < porcentajes.length; i++) {
            g2.setColor(COLORES[i % COLORES.length]);
            g2.fillArc(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2, ultimoAngulo, angulos[i]);

            ultimoAngulo += angulos[i];
        }

        ultimoAngulo = 0;
        for (int i = 0; i < porcentajes.length; i++) {
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(3));

            int x2 = (int) (x + RADIO * Math.cos(Math.toRadians((ultimoAngulo + angulos[i]) * -1)));
            int y2 = (int) (y + RADIO * Math.sin(Math.toRadians((ultimoAngulo + angulos[i]) * -1)));
            
            g2.drawLine(x, y, x2, y2);
            g2.drawArc(x - RADIO, y - RADIO, RADIO * 2, RADIO * 2, ultimoAngulo, angulos[i]);

            ultimoAngulo += angulos[i];
        }

        g2.dispose();
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public static void main(String[] args) {
        JFrame frame;
        int[] porcentajes;

        if (args.length == 0) {
            System.out.println("Falta de parametros: java GraficasPastel.java <PORCENTAJE> <PORCENTAJE> ... <PORCENTAJE>");
            System.exit(0);
        }

        porcentajes = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                porcentajes[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println("Error al parsear el porcentaje: " + args[i]);
                System.exit(1);
            }
        }

        frame = new JFrame("Grafica de Pastel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GraficasPastel(porcentajes));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
