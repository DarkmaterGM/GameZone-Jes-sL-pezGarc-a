package DAO;

import DTO.Cliente;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteD {

    private final Connection connection;

    //Obtiene la conexión a la base de datos
    public ClienteD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // INSERT

    public boolean insertar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO CLIENTE (DNI, Nombre, Apellido1, Apellido2, Telefono, Email, Direccion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido1());
            ps.setString(4, cliente.getApellido2());
            ps.setInt(5, cliente.getTelefono());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getDireccion());
            return ps.executeUpdate() > 0;
        }
    }

    // SELECT BY DNI (Seleccionar por dni)

    public Cliente buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT * FROM CLIENTE WHERE DNI = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    // SELECT ALL

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM CLIENTE ORDER BY Apellido1, Apellido2, Nombre";
        List<Cliente> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // UPDATE

    public boolean actualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE CLIENTE " +
                     "SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, Telefono = ?, Email = ?, Direccion = ? " +
                     "WHERE DNI = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setInt(4, cliente.getTelefono());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getDni());
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM CLIENTE WHERE DNI = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }

    // HELPER

    private Cliente mapear(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("DNI"),
                rs.getString("Nombre"),
                rs.getString("Apellido1"),
                rs.getString("Apellido2"),
                rs.getInt("Telefono"),
                rs.getString("Email"),
                rs.getString("Direccion")
        );
    }
}