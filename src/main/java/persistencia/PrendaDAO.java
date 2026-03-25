/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrendaDAO {

    public void insertarPrenda(Prenda p) {
        String sql = "INSERT INTO prendas (referencia, color, marca, talla, valor_alquiler, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getReferencia());
            stmt.setString(2, p.getColor());
            stmt.setString(3, p.getMarca());
            stmt.setString(4, p.getTalla());
            stmt.setDouble(5, p.getValorAlquiler());
            stmt.setString(6, p.getClass().getSimpleName()); // tipo dinámico
            stmt.executeUpdate();
            System.out.println("Prenda insertada: " + p.getReferencia());
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    public List<Prenda> obtenerPrendas() {
        List<Prenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM prendas";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Prenda p = null;
                switch (tipo.toLowerCase()) {
                    case "vestido":
                        p = new VestidoDama(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                true, "largo", 2 // valores de prueba
                        );
                        break;
                    case "traje":
                        p = new TrajeCaballero(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                "frac", "corbata"
                        );
                        break;
                    case "disfraz":
                        p = new Disfraz(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                "Superheroe"
                        );
                        break;
                }
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public Prenda obtenerPrendaPorReferencia(String ref) {
        String sql = "SELECT * FROM prendas WHERE referencia = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ref);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String color = rs.getString("color");
                String marca = rs.getString("marca");
                String talla = rs.getString("talla");
                double valor = rs.getDouble("valor_alquiler");

                switch (tipo.toLowerCase()) {
                    case "vestido":
                        return new VestidoDama(ref, color, marca, talla, valor, true, "largo", 2);
                    case "traje":
                        return new TrajeCaballero(ref, color, marca, talla, valor, "formal", "algodón");
                    case "disfraz":
                        return new Disfraz(ref, color, marca, talla, valor, "Halloween");
                    default:
                        return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<Prenda> obtenerPrendasPorTalla(String talla) {
        List<Prenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM prendas WHERE talla = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, talla);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                Prenda p = null;
                switch (tipo.toLowerCase()) {
                    case "vestido":
                    case "vestidodama":
                        p = new VestidoDama(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                true, "largo", 2
                        );
                        break;
                    case "traje":
                    case "trajecaballero":
                        p = new TrajeCaballero(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                "frac", "corbata"
                        );
                        break;
                    case "disfraz":
                        p = new Disfraz(
                                rs.getString("referencia"),
                                rs.getString("color"),
                                rs.getString("marca"),
                                rs.getString("talla"),
                                rs.getDouble("valor_alquiler"),
                                "Superheroe"
                        );
                        break;
                }
                if (p != null) {
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
