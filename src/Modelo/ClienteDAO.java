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
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author USUARIO
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarCliente(Cliente cl){
        String sql = "INSERT INTO cliente (nombre, telefono, rfc, direccion) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getTelefono());
            ps.setString(3, cl.getRfc());
            ps.setString(4, cl.getDireccion());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    public List ListarCliente(){
       List<Cliente> ListaCl = new ArrayList();
       String sql = "SELECT * FROM cliente";
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {               
               Cliente cl = new Cliente();
               cl.setId_cliente(rs.getInt("id_cliente"));
               cl.setNombre(rs.getString("nombre"));
               cl.setTelefono(rs.getString("telefono"));
               cl.setRfc(rs.getString("rfc"));
               cl.setDireccion(rs.getString("direccion"));
               ListaCl.add(cl);
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return ListaCl;
   }
    
    public boolean EliminarCliente(String rfc) {
    String sql = "DELETE FROM cliente WHERE rfc = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, rfc);
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
    
    public boolean ModificarCliente(Cliente cl) {
    String sql = "UPDATE cliente SET nombre=?, telefono=?, direccion=? WHERE rfc=?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, cl.getNombre());
        ps.setString(2, cl.getTelefono());
        ps.setString(3, cl.getDireccion());
        ps.setString(4, cl.getRfc());
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
    
    public Cliente BuscarClientePorDNI(String dni) {
    Cliente cl = new Cliente();
    String sql = "SELECT * FROM cliente WHERE dni = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, dni);
        rs = ps.executeQuery();
        if (rs.next()) {
            cl.setId_cliente(rs.getInt("id_cliente"));
            cl.setNombre(rs.getString("nombre"));
            cl.setTelefono(rs.getString("telefono"));
            cl.setRfc(rs.getString("rfc"));
            cl.setDireccion(rs.getString("direccion"));
            cl.setDni(rs.getString("dni"));
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar cliente por DNI: " + e.toString());
    }
    return cl;
}
    
    public Cliente BuscarClientePorId(int id) {
    Cliente cl = new Cliente();
    String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            cl.setId_cliente(rs.getInt("id_cliente"));
            cl.setNombre(rs.getString("nombre"));
            cl.setTelefono(rs.getString("telefono"));
            cl.setDireccion(rs.getString("direccion"));
            cl.setDni(rs.getString("dni"));
            cl.setRfc(rs.getString("rfc"));
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return cl;
}
}
