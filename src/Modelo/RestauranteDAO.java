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
/**
 *
 * @author USUARIO
 */
public class RestauranteDAO {
    private LinkedHashMap<Integer, Restaurante> restaurantesMap = new LinkedHashMap<>();
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Cargar todos los restaurantes desde la base de datos
    public void cargarRestaurantes() {
    restaurantesMap.clear();
    String sql = "SELECT * FROM restaurante";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Restaurante r = new Restaurante();
            r.setId_restaurante(rs.getInt("id_restaurante"));
            r.setNombre(rs.getString("nombre"));
            r.setDireccion(rs.getString("direccion"));
            r.setTelefono(rs.getString("telefono"));
            r.setDni(rs.getString("dni"));
            restaurantesMap.put(r.getId_restaurante(), r);
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
}

    // Registrar restaurante
    public boolean RegistrarRestaurante(Restaurante r){
    String sql = "INSERT INTO restaurante (nombre, direccion, telefono, dni) VALUES (?,?,?,?)";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, r.getNombre());
        ps.setString(2, r.getDireccion());
        ps.setString(3, r.getTelefono());
        ps.setString(4, r.getDni());
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    } finally {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}

    // Listar restaurantes
    public List<Restaurante> ListarRestaurante(){
    List<Restaurante> Lista = new ArrayList<>();
    String sql = "SELECT * FROM restaurante";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Restaurante r = new Restaurante();
            r.setId_restaurante(rs.getInt("id_restaurante"));
            r.setNombre(rs.getString("nombre"));
            r.setDireccion(rs.getString("direccion"));
            r.setTelefono(rs.getString("telefono"));
            r.setDni(rs.getString("dni"));
            Lista.add(r);
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return Lista;
}
    // Buscar restaurante por ID
    public Restaurante buscarRestaurante(int id) {
        return restaurantesMap.get(id);
    }

    // Actualizar restaurante
    public boolean ActualizarRestaurante(Restaurante r){
    String sql = "UPDATE restaurante SET nombre=?, direccion=?, telefono=? WHERE dni=?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, r.getNombre());
        ps.setString(2, r.getDireccion());
        ps.setString(3, r.getTelefono());
        ps.setString(4, r.getDni());
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

    // Eliminar restaurante
    public boolean EliminarRestaurante(String dni){
    String sql = "DELETE FROM restaurante WHERE dni = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, dni);
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
    
public int obtenerIdPorNombre(String nombreRestaurante) {
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
        System.out.println("Error al obtener ID del restaurante: " + e.toString());
    }
    return id;
}

 
}
