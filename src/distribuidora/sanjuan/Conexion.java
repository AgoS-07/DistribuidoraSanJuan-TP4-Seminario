package distribuidora.sanjuan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/distribuidora_san_juan";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "123456";

    // Única instancia de la clase Conexion
    private static Conexion instancia;

    // Constructor privado para aplicar Singleton
    private Conexion() {
    }

    // Devuelve la única instancia de Conexion
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Establece la conexión con MySQL
    public Connection conectarBD() {
        try {
            Connection conexion =
                    DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            System.out.println("Conexion exitosa a MySQL!");
            return conexion;

        } catch (SQLException e) {
            System.out.println(
                    "Error de conexion: " + e.getMessage()
            );
            return null;
        }
    }

    // Mantiene compatibilidad con el código existente
    public static Connection conectar() {
        return getInstancia().conectarBD();
    }
}