package distribuidora.sanjuan;

public abstract class ArticuloInventario {

    private int idProducto;
    private String nombre;

    public ArticuloInventario(int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String obtenerEstadoStock();
}