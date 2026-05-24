package DAO;

import DTO.Venta;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaD {

    private final Connection connection;

    public VentaD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // ── INSERT ─────────────────────────────────────────────────────────────────

    public boolean insertar(Venta venta) throws SQLException {
        String sql = "INSERT INTO VENTA (id, Fecha_compra, id_cliente, id_empleado, Importe) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, venta.getId());
            ps.setDate(2, venta.getFecha_compra());
            ps.setString(3, venta.getId_cliente());
            ps.setInt(4, venta.getId_empleado());
            ps.setDouble(5, venta.getImporte());
            return ps.executeUpdate() > 0;
        }
    }

    // ── SELECT BY ID ───────────────────────────────────────────────────────────

    public Venta buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM VENTA WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    // ── SELECT ALL ─────────────────────────────────────────────────────────────

    public List<Venta> listarTodos() throws SQLException {
        String sql = "SELECT * FROM VENTA ORDER BY Fecha_compra DESC";
        List<Venta> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // ── SELECT BY CLIENTE ──────────────────────────────────────────────────────

    public List<Venta> listarPorCliente(String dniCliente) throws SQLException {
        String sql = "SELECT * FROM VENTA WHERE id_cliente = ? ORDER BY Fecha_compra DESC";
        List<Venta> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dniCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // ── SELECT BY EMPLEADO ─────────────────────────────────────────────────────

    public List<Venta> listarPorEmpleado(int idEmpleado) throws SQLException {
        String sql = "SELECT * FROM VENTA WHERE id_empleado = ? ORDER BY Fecha_compra DESC";
        List<Venta> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // ── UPDATE ─────────────────────────────────────────────────────────────────

    public boolean actualizar(Venta venta) throws SQLException {
        String sql = "UPDATE VENTA " +
                     "SET Fecha_compra = ?, id_cliente = ?, id_empleado = ?, Importe = ? " +
                     "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, venta.getFecha_compra());
            ps.setString(2, venta.getId_cliente());
            ps.setInt(3, venta.getId_empleado());
            ps.setDouble(4, venta.getImporte());
            ps.setInt(5, venta.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // ── DELETE ─────────────────────────────────────────────────────────────────

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM VENTA WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // ── HELPER ─────────────────────────────────────────────────────────────────

    private Venta mapear(ResultSet rs) throws SQLException {
        return new Venta(
                rs.getInt("id"),
                rs.getDate("Fecha_compra"),
                rs.getString("id_cliente"),
                rs.getInt("id_empleado"),
                rs.getDouble("Importe")
        );
    }
}