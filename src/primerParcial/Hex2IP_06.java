package primerParcial;

public class Hex2IP_06 {

    // La misma clase define el método main.
    public static void main(String[] args) {
        // Verificar que contenga la opcion y el parametro al iniciar el programa
        if (args.length != 2) {
            System.out.println("Favor de ingresar una opcion y un parametro: -hex \"cadena_hexadecimal\" o -ip \"direccion_ip\"");
            return;
        }

        // Capturar los parametros
        String opcion = args[0];
        String cadena = args[1];

        Hex2IPconvertidora objHex2IP = new Hex2IPconvertidora(opcion, cadena);
        if (objHex2IP.getResultado() != null) {
            System.out.println(objHex2IP.getResultado());
        }
    }
}

class Hex2IPconvertidora {

    private String resultado;

    public Hex2IPconvertidora(String opcion, String cadena) {

        // Validaciones de los parametros -hex y -ip
        if ("-hex".equalsIgnoreCase(opcion)) {
            convertirHexadecimalADireccionIP(cadena);
        } else if ("-ip".equalsIgnoreCase(opcion)) {
            convertirDireccionIPAHexagecimal(cadena);
        } else {
            System.out.println("Favor de ingresar una opcion y un parametro validos: -hex \"cadena_hexadecimal\" o -ip \"direccion_ip\"");
        }
    }

    // Metodo para convertir una cadena hexadecimal a una direccion IP no estatico
    public void convertirHexadecimalADireccionIP(String hex) {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hex.length(); i = i + 2) {
                String part = hex.substring(i, i + 2);
                int ippart = Integer.parseInt(part, 16);

                sb.append(ippart);
                if (i + 2 < hex.length()) {
                    sb.append(".");
                }
            }
            resultado = sb.toString();
        } catch (NumberFormatException e) {
            System.out.println("Error: Cadena hexadecimal no valida.");
            resultado = null;
        }
    }

    // Metodo para convertir una direccion IP a una cadena hexadecimal no estatico
    public void convertirDireccionIPAHexagecimal(String ip) {
        String[] partes = ip.split("\\.");
        if (partes.length != 4) {
            resultado = null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < partes.length; i++) {
                int temp = Integer.parseInt(partes[i]);
                if (temp > 0 && temp <= 255) {
                    sb.append(String.format("%02X", temp));
                }
            }
            resultado = sb.toString();
        } catch (NumberFormatException e) {
            resultado = null;
        }
    }

    public String getResultado() {
        return resultado;
    }

}
