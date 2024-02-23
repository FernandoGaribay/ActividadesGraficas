package primerParcial;

import javax.swing.*;

public class Ventana_07 {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Mi Ventana (JFrame)");

        // Se establece las dimenciones la ventana
        ventana.setSize(400, 300);
        // Asegura que la ventana no se pueda redimencionar
        ventana.setResizable(false);
        // La aplicacion finaliza cuando se cierra la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Asegura que la ventana se muestre en el centro de la pantalla
        ventana.setLocationRelativeTo(null);
        // Hace visible la ventana
        ventana.setVisible(true);
    }
}
