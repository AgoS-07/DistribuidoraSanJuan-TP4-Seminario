package distribuidora.sanjuan;

public class OpcionesInventario {

    private String[] opciones;

    public OpcionesInventario() {

        opciones = new String[]{
            "Gestion de Productos",
            "Consultar Stock Bajo",
            "Salir"
        };
    }

    public String[] obtenerOpciones() {
        return opciones;
    }

    public void mostrarOpciones() {

        System.out.println("--- OPCIONES DEL SISTEMA ---");

        for (int i = 0; i < opciones.length; i++) {
            System.out.println(
                    (i + 1) + " - " + opciones[i]
            );
        }
    }
}