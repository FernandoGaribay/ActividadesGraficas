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
import javax.swing.JPanel;
import javax.swing.Timer;

public class EspiralArquimides_08 extends JPanel implements Runnable {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private final double RADIO_INCREMENTO = 0.5;
    private final double ANGULO_INCREMENTO = 0.1;
    private final double ANGULO_FIN = 12 * Math.PI;
    private final int DELAY = 10;

    private double angulo = 0.0;
    private double radio = 10;
    private int CENTER_X = WIDTH / 2;
    private int CENTER_Y = HEIGHT / 2 - 15; // 15 es la mitad del height del boton

    private Timer timer;
    private BufferedImage buffer;

    public EspiralArquimides_08() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton button = new JButton("Iniciar");
        button.setPreferredSize(new Dimension(100, 30));
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        setLayout(new BorderLayout());
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
        }

        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        
    }
    
    public void dibujarEspiral() {
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        g2.setColor(Color.BLACK);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (int) (CENTER_X + radio * Math.cos(angulo) * -1);
        int y = (int) (CENTER_Y + radio * Math.sin(angulo)  * -1);
        
        int x2 = (int) (CENTER_X + radio * Math.cos(angulo));
        int y2 = (int) (CENTER_Y + radio * Math.sin(angulo));

        angulo += ANGULO_INCREMENTO;
        radio += RADIO_INCREMENTO;

        int xNext = (int) (CENTER_X + radio * Math.cos(angulo) * -1);
        int yNext = (int) (CENTER_Y + radio * Math.sin(angulo) * -1);
        
        int x2Next = (int) (CENTER_X + radio * Math.cos(angulo));
        int y2Next = (int) (CENTER_Y + radio * Math.sin(angulo));

        g2.setColor(Color.BLACK);
        g2.drawLine(x, y, xNext, yNext);
        g2.drawLine(x2, y2, x2Next, y2Next);
        g2.drawLine((int)(CENTER_X - 10), CENTER_Y, (int) (CENTER_X + 10), CENTER_Y);
        
        g2.dispose();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Espiral de Arquimedes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new EspiralArquimides_08());
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
