package modelo;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private double precio;
    private int usuariosIdUsuario;

    public Producto() {}

    public Producto(String nombreProducto, double precio, int usuariosIdUsuario) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getUsuariosIdUsuario() { return usuariosIdUsuario; }
    public void setUsuariosIdUsuario(int usuariosIdUsuario) { this.usuariosIdUsuario = usuariosIdUsuario; }
}