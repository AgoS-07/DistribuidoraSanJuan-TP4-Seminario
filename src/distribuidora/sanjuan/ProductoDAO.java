package distribuidora.sanjuan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void listarProductos() {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {
            try {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM producto");

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("idProducto") + " - "
                            + rs.getString("nombre") + " - $"
                            + rs.getDouble("precio")
                    );
                }

                conexion.close();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public List<Producto> obtenerProductos() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try (Connection conexion = Conexion.conectar();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Producto producto = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("stockMinimo"),
                        rs.getInt("idProveedor")
                );

                productos.add(producto);
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al obtener productos: " + e.getMessage()
            );
        }

        return productos;
    }

    public void agregarProducto(Producto producto) {

        String sql = "INSERT INTO producto "
                + "(nombre, descripcion, precio, stock, stockMinimo, idProveedor) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             java.sql.PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdProveedor());

            ps.executeUpdate();

            System.out.println("Producto agregado correctamente!");

        } catch (Exception e) {
            System.out.println(
                    "Error al agregar producto: " + e.getMessage()
            );
        }
    }

    public void modificarProducto(Producto producto) {

        String sql = "UPDATE producto SET "
                + "nombre = ?, "
                + "descripcion = ?, "
                + "precio = ?, "
                + "stock = ?, "
                + "stockMinimo = ?, "
                + "idProveedor = ? "
                + "WHERE idProducto = ?";

        try (Connection conexion = Conexion.conectar();
             java.sql.PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getIdProveedor());
            ps.setInt(7, producto.getIdProducto());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto modificado correctamente!");
            } else {
                System.out.println("No se encontro el producto.");
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al modificar producto: " + e.getMessage()
            );
        }
    }

    public void eliminarProducto(int idProducto) {

        String sql = "DELETE FROM producto WHERE idProducto = ?";

        try (Connection conexion = Conexion.conectar();
             java.sql.PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Producto eliminado correctamente!");
            } else {
                System.out.println("No se encontro el producto.");
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al eliminar producto: " + e.getMessage()
            );
        }
    }

    // Consulta los productos cuyo stock está por debajo del mínimo
    public void listarProductosStockBajo() {

        String sql = "SELECT idProducto, nombre, stock, stockMinimo "
                + "FROM producto WHERE stock < stockMinimo";

        try (Connection conexion = Conexion.conectar();
             Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("--- PRODUCTOS CON STOCK BAJO ---");

            boolean encontrados = false;

            while (rs.next()) {

                encontrados = true;

                System.out.println(
                        "ID: " + rs.getInt("idProducto")
                        + " | Producto: " + rs.getString("nombre")
                        + " | Stock: " + rs.getInt("stock")
                        + " | Stock mínimo: " + rs.getInt("stockMinimo")
                );
            }

            if (!encontrados) {
                System.out.println("No hay productos con stock bajo.");
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al consultar stock bajo: " + e.getMessage()
            );
        }
    }
}