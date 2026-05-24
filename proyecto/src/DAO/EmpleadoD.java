package DAO;

import DTO.Empleado;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoD {

    private final Connection connection;

    //Obtiene la conexión a la base de datos
    public EmpleadoD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // INSERT

    public boolean insertar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO EMPLEADO (id, Nombre, Apellido1, Apellido2, Fecha_contratacion, Cargo, anios_experiencia) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, empleado.getId());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellido1());
            ps.setString(4, empleado.getApellido2());
            ps.setDate(5, empleado.getFecha_contratacion());
            ps.setString(6, empleado.getCargo());
            ps.setInt(7, empleado.getAnios_experiencia());
            return ps.executeUpdate() > 0;
        }
    }

    // SELECT BY ID (Seleccionar por id)

    public Empleado buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM EMPLEADO WHERE id = ?";

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

    // SELECT ALL

    public List<Empleado> listarTodos() throws SQLException {
        String sql = "SELECT * FROM EMPLEADO ORDER BY Apellido1, Apellido2, Nombre";
        List<Empleado> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // SELECT BY CARGO (Seleccionar por cargo)

    public List<Empleado> listarPorCargo(String cargo) throws SQLException {
        String sql = "SELECT * FROM EMPLEADO WHERE Cargo = ? ORDER BY Apellido1, Nombre";
        List<Empleado> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cargo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // UPDATE (Actualizar ficha de empleado)

    public boolean actualizar(Empleado empleado) throws SQLException {
        String sql = "UPDATE EMPLEADO " +
                     "SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, " +
                     "Fecha_contratacion = ?, Cargo = ?, anios_experiencia = ? " +
                     "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido1());
            ps.setString(3, empleado.getApellido2());
            ps.setDate(4, empleado.getFecha_contratacion());
            ps.setString(5, empleado.getCargo());
            ps.setInt(6, empleado.getAnios_experiencia());
            ps.setInt(7, empleado.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM EMPLEADO WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // HELPER

    private Empleado mapear(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getInt("id"),
                rs.getString("Nombre"),
                rs.getString("Apellido1"),
                rs.getString("Apellido2"),
                rs.getDate("Fecha_contratacion"),
                rs.getString("Cargo"),
                rs.getInt("anios_experiencia")
        );
    }
}