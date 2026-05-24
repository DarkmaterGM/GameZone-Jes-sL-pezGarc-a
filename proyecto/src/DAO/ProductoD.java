package DAO;

import DTO.Producto;
import Main.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoD {

    private final Connection connection;

    //Obtiene la conexión a la base de datos
    public ProductoD() throws SQLException {
        this.connection = ConexionBD.getInstance().getConnection();
    }

    // INSERT 

    public boolean insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO PRODUCTO (id, Nombre, Plataforma, id_categoria, Precio, Stock, Fecha_lanzamiento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, producto.getId());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getPlataforma());
            ps.setInt(4, producto.getId_categoria());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getStock());
            ps.setDate(7, producto.getFecha_lanzamiento());
            return ps.executeUpdate() > 0;
        }
    }

    // SELECT BY ID (Seleccionar por id)

    public Producto buscarPorId(String id) throws SQLException {
        String sql = "SELECT * FROM PRODUCTO WHERE id = ?";

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

    public List<Producto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM PRODUCTO ORDER BY Nombre";
        List<Producto> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // SELECT BY CATEGORÍA (Seleccionar por categoria)

    public List<Producto> listarPorCategoria(int idCategoria) throws SQLException {
        String sql = "SELECT * FROM PRODUCTO WHERE id_categoria = ? ORDER BY Nombre";
        List<Producto> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        }
        return lista;
    }

    // UPDATE

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE PRODUCTO " +
                     "SET Nombre = ?, Plataforma = ?, id_categoria = ?, Precio = ?, Stock = ?, Fecha_lanzamiento = ? " +
                     "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getPlataforma());
            ps.setInt(3, producto.getId_categoria());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setDate(6, producto.getFecha_lanzamiento());
            ps.setString(7, producto.getId());
            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE STOCK (Actualizar stock)

    public boolean actualizarStock(String id, int nuevoStock) throws SQLException {
        String sql = "UPDATE PRODUCTO SET Stock = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE 

    public boolean eliminar(String id) throws SQLException {
        String sql = "DELETE FROM PRODUCTO WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // HELPER

    private Producto mapear(ResultSet rs) throws SQLException {
        return new Producto(
                rs.getString("id"),
                rs.getString("Nombre"),
                rs.getString("Plataforma"),
                rs.getInt("id_categoria"),
                rs.getDouble("Precio"),
                rs.getInt("Stock"),
                rs.getDate("Fecha_lanzamiento")
        );
    }
}