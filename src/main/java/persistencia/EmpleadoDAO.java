/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public void insertarEmpleado(Empleado e) {
        String sql = "INSERT INTO empleados (id, nombre, direccion, telefono, cargo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getId());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getDireccion());
            stmt.setString(4, e.getTelefono());
            stmt.setString(5, e.getCargo());
            stmt.executeUpdate();
            System.out.println("Empleado insertado: " + e.getNombre());
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Empleado e = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cargo")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }

    public Empleado obtenerEmpleadoPorId(String id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cargo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
