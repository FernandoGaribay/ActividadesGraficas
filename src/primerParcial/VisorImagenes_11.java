package primerParcial;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VisorImagenes_11 extends JFrame {

    private JScrollPane panel;
    private VisorImagen pantalla;

    public VisorImagenes_11(String archivo) {
        super("Visor Imagen");

        Image img = Toolkit.getDefaultToolkit().getImage(archivo);
        pantalla = new VisorImagen(img);
        panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(panel);
        panel.setViewportView(pantalla);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Falta de parametros: java VisorImagenes_11 <ruta_de_la_imagen>");
            System.exit(1);
        }
        String rutaImagen = args[0];

        VisorImagenes_11 visor = new VisorImagenes_11(rutaImagen);
    }

}

class VisorImagen extends JPanel {

    private Image imagen;

    public VisorImagen(Image imagen) {
        this.imagen = imagen;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension tam = new Dimension(imagen.getWidth(this), imagen.getHeight(this));
        setPreferredSize(tam);
        setMinimumSize(tam);
        setMaximumSize(tam);
        setSize(tam);
        update(g);

    }

    @Override
    public void update(Graphics g) {
        g.drawImage(imagen, 0, 0, this);
    }

}
