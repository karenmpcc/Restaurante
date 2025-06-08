/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class VentaDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int RegistrarVenta(Venta v) {
    int r = 0;
    String sql = "INSERT INTO venta (id_cliente, nombre_cliente, id_restaurante, restaurante, total, fecha) VALUES (?, ?, ?, ?, ?, NOW())";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, v.getId_cliente());
        ps.setString(2, v.getNombre_cliente());
        ps.setInt(3, v.getId_restaurante());
        ps.setString(4, v.getRestaurante());
        ps.setDouble(5, v.getTotal());
        ps.execute();
        r = 1;
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return r;
}
    
    public int RegistrarDetalle(Detalle detalle) {
    int r = 0;
    String sql = "INSERT INTO detalle (id_producto, cantidad, precio, id_venta) VALUES (?,?,?,?)";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, detalle.getId_producto());
        ps.setInt(2, detalle.getCantidad());
        ps.setDouble(3, detalle.getPrecio());
        ps.setInt(4, detalle.getId_venta());
        r = ps.executeUpdate(); // devuelve 1 si se insertó correctamente
    } catch (SQLException e) {
        System.out.println("Error al registrar detalle: " + e.toString());
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.toString());
        }
    }
    return r;
}
    public int IdVenta() {
    int id = 0;
    String sql = "SELECT MAX(id_venta) AS id FROM venta";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    return id;
}
}
