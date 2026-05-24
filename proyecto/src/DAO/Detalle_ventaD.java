package DAO;

import DTO.Detalle_venta;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Detalle_ventaD {

    private final Connection connection;

    //Obtiene la conexión a la base de datos
    public Detalle_ventaD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // INSERT

    public boolean insertar(Detalle_venta detalle) throws SQLException {
        String sql = "INSERT INTO DETALLE_VENTA (id_venta, id_producto, Cantidad, Precio_unitario) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, detalle.getId_venta());
            ps.setInt(2, detalle.getId_producto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio_unitario());
            return ps.executeUpdate() > 0;
        }
    }

    // INSERT BATCH (todos los detalles de una venta de una sola vez)

    public void insertarLote(List<Detalle_venta> detalles) throws SQLException {
        String sql = "INSERT INTO DETALLE_VENTA (id_venta, id_producto, Cantidad, Precio_unitario) " +
                     "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Detalle_venta detalle : detalles) {
                ps.setInt(1, detalle.getId_venta());
                ps.setInt(2, detalle.getId_producto());
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecio_unitario());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // SELECT BY PK (id_venta + id_producto)

    public Detalle_venta buscarPorPk(int idVenta, int idProducto) throws SQLException {
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_venta = ? AND id_producto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.setInt(2, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    // SELECT BY VENTA (Seleccionar por el id de venta)

    public List<Detalle_venta> listarPorVenta(int idVenta) throws SQLException {
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_venta = ?";
        List<Detalle_venta> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // SELECT BY PRODUCTO (Seleccionar por el id del producto)

    public List<Detalle_venta> listarPorProducto(int idProducto) throws SQLException {
        String sql = "SELECT * FROM DETALLE_VENTA WHERE id_producto = ?";
        List<Detalle_venta> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // UPDATE

    public boolean actualizar(Detalle_venta detalle) throws SQLException {
        String sql = "UPDATE DETALLE_VENTA " +
                     "SET Cantidad = ?, Precio_unitario = ? " +
                     "WHERE id_venta = ? AND id_producto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, detalle.getCantidad());
            ps.setDouble(2, detalle.getPrecio_unitario());
            ps.setInt(3, detalle.getId_venta());
            ps.setInt(4, detalle.getId_producto());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE BY PK

    public boolean eliminar(int idVenta, int idProducto) throws SQLException {
        String sql = "DELETE FROM DETALLE_VENTA WHERE id_venta = ? AND id_producto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.setInt(2, idProducto);
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE BY VENTA

    public boolean eliminarPorVenta(int idVenta) throws SQLException {
        String sql = "DELETE FROM DETALLE_VENTA WHERE id_venta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            return ps.executeUpdate() > 0;
        }
    }

    // HELPER
    // Nota: el DTO tiene campo 'id' pero la tabla no. Se pasa 0 como placeholder.

    private Detalle_venta mapear(ResultSet rs) throws SQLException {
        return new Detalle_venta(
                0,
                rs.getInt("id_venta"),
                rs.getInt("id_producto"),
                rs.getInt("Cantidad"),
                rs.getDouble("Precio_unitario")
        );
    }
}