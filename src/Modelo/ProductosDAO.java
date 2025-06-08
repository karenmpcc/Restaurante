/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;


public class ProductosDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistrarProducto(Productos p) {
    String sql = "INSERT INTO productos (nombre, descripcion, precio, id_restaurante, codigo, stock) VALUES (?,?,?,?,?,?)";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDescripcion());
        ps.setDouble(3, p.getPrecio());
        ps.setInt(4, p.getId_restaurante());
        ps.setString(5, p.getCodigo());
        ps.setInt(6, p.getStock());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

    public List<Productos> ListarProductos() {
    List<Productos> Lista = new ArrayList<>();
    String sql = "SELECT p.*, r.nombre AS restaurante_nombre FROM productos p INNER JOIN restaurante r ON p.id_restaurante = r.id_restaurante";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Productos p = new Productos();
            p.setId_producto(rs.getInt("id_producto"));
            p.setCodigo(rs.getString("codigo"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getDouble("precio"));
            p.setId_restaurante(rs.getInt("id_restaurante"));
            p.setStock(rs.getInt("stock"));
            p.setRestauranteNombre(rs.getString("restaurante_nombre"));
            Lista.add(p);
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return Lista;
}


    public boolean ActualizarProducto(Productos p) {
    String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, id_restaurante=?, codigo=?, stock=? WHERE id_producto=?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getDescripcion());
        ps.setDouble(3, p.getPrecio());
        ps.setInt(4, p.getId_restaurante());
        ps.setString(5, p.getCodigo());
        ps.setInt(6, p.getStock());
        ps.setInt(7, p.getId_producto());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

    public boolean EliminarProducto(String codigo) {
    String sql = "DELETE FROM productos WHERE codigo = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, codigo);
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
}

    public Productos BuscarPorCodigo(String codigo) {
    Productos producto = new Productos();
    String sql = "SELECT * FROM productos WHERE codigo = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, codigo);
        rs = ps.executeQuery();
        if (rs.next()) {
            producto.setId_producto(rs.getInt("id_producto"));
            producto.setCodigo(rs.getString("codigo"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setId_restaurante(rs.getInt("id_restaurante"));
            producto.setStock(rs.getInt("stock"));
            producto.setPrecio(rs.getDouble("precio"));
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return producto;
}
    
    public void ConsultarRestaurantes(JComboBox restauranteCombo) {
    String sql = "SELECT nombre FROM restaurante";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        restauranteCombo.removeAllItems(); 
        while (rs.next()) {
            restauranteCombo.addItem(rs.getString("nombre"));
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
}

    
    public int obtenerIdPorNombre(String nombre) {
    String sql = "SELECT id_restaurante FROM restaurante WHERE nombre = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_restaurante");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return -1;
}
    
    public int ObtenerIdRestaurante(String nombreRestaurante) {
    int id = -1;
    String sql = "SELECT id_restaurante FROM restaurante WHERE nombre = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, nombreRestaurante);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id_restaurante");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener ID del restaurante: " + e);
    }
    return id;
}

}
