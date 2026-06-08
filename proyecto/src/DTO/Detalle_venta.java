package DTO;

public class Detalle_venta {
    private int id_venta;
    private String id_producto;
    private int cantidad;
    private double precio_unitario;

    public Detalle_venta(int id_venta, String id_producto, int cantidad, double precio_unitario) {
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getId_venta() { return id_venta; }
    public void setId_venta(int id_venta) { this.id_venta = id_venta; }

    public String getId_producto() { return id_producto; }
    public void setId_producto(String id_producto) { this.id_producto = id_producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(double precio_unitario) { this.precio_unitario = precio_unitario; }

    @Override
    public String toString() {
        return "Detalle_venta [id_venta=" + id_venta + ", id_producto=" + id_producto +
               ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + "]";
    }
}