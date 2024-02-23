package primerParcial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class EspiralArquimides_08 extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private final double RADIO_INCREMENTO = 0.5;
    private final double ANGULO_INCREMENTO = 0.2;
    private final double ANGULO_FIN = 20 * Math.PI;
    private final int DELAY = 20;

    private double angulo = 0.0;
    private double radio = 10;
    private int centerX = WIDTH / 2;
    private int centerY = HEIGHT / 2;

    private Timer timer;
    private BufferedImage buffer;
    private BufferedImage drawBuffer;

    public EspiralArquimides_08() {
        super("Espiral de Arquímedes");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Iniciar");
        button.setPreferredSize(new Dimension(100, 30));
        button.setFocusPainted(false); // Remueve el borde de enfoque
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = new Timer(DELAY, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dibujarEspiral();
                        repaint();

                        if (angulo >= ANGULO_FIN) {
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (buffer == null) {
            buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
            drawBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        }

        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public void dibujarEspiral() {
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x1 = (int) (centerX + radio * Math.cos(angulo));
        int y1 = (int) (centerY + radio * Math.sin(angulo));

        angulo += ANGULO_INCREMENTO;
        radio += RADIO_INCREMENTO;

        int x2 = (int) (centerX + radio * Math.cos(angulo));
        int y2 = (int) (centerY + radio * Math.sin(angulo));

        g2.setColor(new Color(0, 0, 0));
        g2.drawLine(x1, y1, x2, y2);
        g2.dispose();

//        Graphics2D g2d = (Graphics2D) drawBuffer.getGraphics();
//        g2d.drawImage(buffer, 0, 0, null);
//        g2d.dispose();
    }

    public static void main(String[] args) {
        EspiralArquimides_08 ventana = new EspiralArquimides_08();
        ventana.setVisible(true);
    }
}
