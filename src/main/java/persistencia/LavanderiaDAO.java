/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author Oscar Danilo Melo
 */
import java.sql.*;
import java.util.*;

public class LavanderiaDAO {

    // Registrar prenda en la lista de lavandería
    public void registrarPrendaLavanderia(String referenciaPrenda, boolean prioridad) {
        String sql = "INSERT INTO prendas_lavanderia (referencia_prenda, prioridad) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, referenciaPrenda);
            stmt.setBoolean(2, prioridad);
            stmt.executeUpdate();
            System.out.println("Prenda registrada en lavanderia: " + referenciaPrenda
                    + (prioridad ? " (PRIORIDAD)" : ""));
        } catch (SQLException e) {
            System.out.println("Error al registrar prenda en lavanderia: " + e.getMessage());
        }
    }

    public List<String> obtenerPrendasLavanderia() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT referencia_prenda, prioridad, fecha_registro FROM prendas_lavanderia ORDER BY prioridad DESC, fecha_registro ASC";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String ref = rs.getString("referencia_prenda");
                boolean prioridad = rs.getBoolean("prioridad");
                Timestamp fecha = rs.getTimestamp("fecha_registro");
                String registro = ref + (prioridad ? " (PRIORIDAD)" : "") + " - Registrada: " + fecha;
                lista.add(registro);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener prendas de lavandería: " + e.getMessage());
        }
        return lista;
    }

    public List<String> enviarPrendasLavanderia(int cantidad) {
        List<String> enviadas = new ArrayList<>();
        String sqlSelect = "SELECT id, referencia_prenda, prioridad, fecha_registro "
                + "FROM prendas_lavanderia ORDER BY prioridad DESC, fecha_registro ASC LIMIT ?";
        String sqlDelete = "DELETE FROM prendas_lavanderia WHERE id = ?";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, cantidad);
            ResultSet rs = stmtSelect.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String ref = rs.getString("referencia_prenda");
                boolean prioridad = rs.getBoolean("prioridad");
                Timestamp fecha = rs.getTimestamp("fecha_registro");

                String registro = ref + (prioridad ? " (PRIORIDAD)" : "") + " - Enviada: " + fecha;
                enviadas.add(registro);

                // Eliminar de la lista
                try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
                    stmtDelete.setInt(1, id);
                    stmtDelete.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al enviar prendas a lavandería: " + e.getMessage());
        }
        return enviadas;
    }
}
