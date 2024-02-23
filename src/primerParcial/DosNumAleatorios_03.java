package primerParcial;

public class DosNumAleatorios_03 {
    
    public static void main(String[] args) {
        // Generar numeros aleatorios entre 0 y 100
        int num1 = (int) (Math.random() * 101);
        int num2 = (int) (Math.random() * 101); 
        
        // Imprimir los numeros generados
        System.out.println("Numero 1: " + num1);
        System.out.println("Numero 2: " + num2);
        
        // Determinar cual numero es mayor
        if (num1 > num2) {
            System.out.println("El numero " + num1 + " es mayor que el numero " + num2);
        } else if (num2 > num1) {
            System.out.println("El numero " + num2 + " es mayor que el numero " + num1);
        } else {
            System.out.println("Ambos números son iguales: " + num1);
        }
    }
}
