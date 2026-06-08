package DAO;

import DTO.Categoria;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaD {

    private final Connection connection;

    //Obtiene la conexión a la base de datos
    public CategoriaD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // INSERT

    public boolean insertar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (id, Nombre, Descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoria.getId());
            ps.setString(2, categoria.getNombre());
            ps.setString(3, categoria.getDescripcion());
            return ps.executeUpdate() > 0;
        }
    }

    // SELECT BY ID (Seleccionar por id)

    public Categoria buscarPorId(String id) throws SQLException {
        String sql = "SELECT id, Nombre, Descripcion FROM CATEGORIA WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    // SELECT ALL

    public List<Categoria> listarTodos() throws SQLException {
        String sql = "SELECT id, Nombre, Descripcion FROM CATEGORIA ORDER BY Nombre";
        List<Categoria> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // UPDATE

    public boolean actualizar(String id, Categoria categoria) throws SQLException {
        String sql = "UPDATE CATEGORIA SET id = ? Nombre = ?, Descripcion = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
        	ps.setString(1, categoria.getId());
            ps.setString(2, categoria.getNombre());
            ps.setString(3, categoria.getDescripcion());
            
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE

    public boolean eliminar(String id) throws SQLException {
        String sql = "DELETE FROM CATEGORIA WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // HELPER

    private Categoria mapear(ResultSet rs) throws SQLException {
        return new Categoria(
        		rs.getString("id"),
                rs.getString("Nombre"),
                rs.getString("Descripcion")
        );
    }
}