package distribuidora.sanjuan;

public class Producto extends ArticuloInventario {

    private String descripcion;
    private double precio;
    private int stock;
    private int stockMinimo;
    private int idProveedor;

    public Producto(int idProducto, String nombre, String descripcion,
                    double precio, int stock, int stockMinimo, int idProveedor) {

        super(idProducto, nombre);
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.idProveedor = idProveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String obtenerEstadoStock() {
        if (stock < stockMinimo) {
            return "STOCK BAJO";
        } else {
            return "STOCK SUFICIENTE";
        }
    }
}