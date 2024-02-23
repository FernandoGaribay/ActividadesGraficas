package primerParcial;

public class Subcadenas_05 {
    public static void main(String[] args) {
        // Verificar la cadena de texto como argumento al iniciar el programa
        if (args.length == 0) {
            System.out.println("No se proporciono una cadena de texto como argumento.");
            return;
        }
        
        // Obtener la cadena de texto del primer argumento
        String cadena = args[0];
        
        // Imprimir todas las subcadenas de la cadena original
        System.out.println("Subcadenas de la cadena \"" + cadena + "\":\n");
        
        // Generar subcadenas eliminando un caracter a la vez del final
        for (int i = cadena.length(); i >= 1; i--) {
            System.out.println(cadena.substring(0, i));
        }
        
        // Generar subcadenas mostrando un caracter a la vez de derecha a izquierda
        for (int i = cadena.length(); i >= 1; i--) {
            System.out.println(cadena.substring(i - 1));
        }
    }
}
