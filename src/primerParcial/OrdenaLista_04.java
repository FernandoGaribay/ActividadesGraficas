package primerParcial;

public class OrdenaLista_04 {
    public static void main(String[] args) {
        // Verificar la lista de numero como argumento al iniciar el programa
        if (args.length == 0) {
            System.out.println("No se proporcionaron numeros como argumentos.");
            return;
        }
        
        // Convertir los argumentos de tipo String a tipo int
        int[] numeros = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            try {
                numeros[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir el argumento " + args[i] + " a un numero entero.");
                return;
            }
        }
        
        // Ordenar la lista de numeros utilizando los metodo de la clase Math -> Math.min y Math.max
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = i + 1; j < numeros.length; j++) {
                int min = Math.min(numeros[i], numeros[j]);
                int max = Math.max(numeros[i], numeros[j]);
                numeros[i] = min;
                numeros[j] = max;
            }
        }
        
        // Imprimir la lista de numeros ordenados
        System.out.println("Lista de numeros ordenada de menor a mayor:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
