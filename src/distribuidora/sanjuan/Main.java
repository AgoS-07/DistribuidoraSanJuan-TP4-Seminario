package distribuidora.sanjuan;

public class Main {

    public static void main(String[] args) {

        OpcionesInventario opciones = new OpcionesInventario();
        opciones.mostrarOpciones();

        java.awt.EventQueue.invokeLater(() -> {
            FrmMenuPrincipal menu = new FrmMenuPrincipal();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}