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
import java.util.*;

public class ServicioDAO {

    public void insertarServicio(ServicioAlquiler s) {
        String sql = "INSERT INTO servicios (fecha_solicitud, fecha_alquiler, cliente_id, empleado_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, new Timestamp(s.getFechaSolicitud().getTime()));
            stmt.setTimestamp(2, new Timestamp(s.getFechaAlquiler().getTime()));
            stmt.setString(3, s.getCliente().getId());
            stmt.setString(4, s.getEmpleado().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int servicioId = rs.getInt(1);
                for (Prenda p : s.getPrendas()) {
                    insertarPrendaServicio(servicioId, p.getReferencia());
                }
            }
            System.out.println("Servicio insertado con prendas asociadas");
        } catch (SQLException e) {
            System.out.println("Error al insertar: " + e.getMessage());
        }
    }

    private void insertarPrendaServicio(int servicioId, String prendaRef) throws SQLException {
        String sql = "INSERT INTO servicio_prendas (servicio_numero, prenda_referencia) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicioId);
            stmt.setString(2, prendaRef);
            stmt.executeUpdate();
        }
    }

    public List<ServicioAlquiler> obtenerServicios() {
        List<ServicioAlquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios";
        try (Connection conn = ConexionBD.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int numero = rs.getInt("numero");
                java.util.Date fechaAlquiler = rs.getTimestamp("fecha_alquiler");
                String clienteId = rs.getString("cliente_id");
                String empleadoId = rs.getString("empleado_id");

                // Recuperar cliente y empleado desde sus DAOs
                Cliente cliente = new ClienteDAO().obtenerClientePorId(clienteId);
                Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(empleadoId);

                // Recuperar prendas asociadas
                List<Prenda> prendas = obtenerPrendasServicio(numero);

                ServicioAlquiler s = new ServicioAlquiler(fechaAlquiler, cliente, empleado, prendas);
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    private List<Prenda> obtenerPrendasServicio(int servicioId) {
        List<Prenda> prendas = new ArrayList<>();
        String sql = "SELECT prenda_referencia FROM servicio_prendas WHERE servicio_numero = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicioId);
            ResultSet rs = stmt.executeQuery();
            PrendaDAO prendaDAO = new PrendaDAO();
            while (rs.next()) {
                String ref = rs.getString("prenda_referencia");
                Prenda p = prendaDAO.obtenerPrendaPorReferencia(ref);
                if (p != null) {
                    prendas.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return prendas;
    }
    // Consulta por número

    public ServicioAlquiler obtenerServicioPorNumero(int numero) {
        String sql = "SELECT * FROM servicios WHERE numero = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new ClienteDAO().obtenerClientePorId(rs.getString("cliente_id"));
                Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(rs.getString("empleado_id"));
                List<Prenda> prendas = obtenerPrendasServicio(numero);
                return new ServicioAlquiler(
                        rs.getDate("fecha_alquiler"),
                        cliente,
                        empleado,
                        prendas
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

// Consulta por cliente
    public List<ServicioAlquiler> obtenerServiciosPorCliente(String clienteId) {
        List<ServicioAlquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios WHERE cliente_id = ? AND fecha_alquiler >= CURDATE() ORDER BY fecha_alquiler";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new ClienteDAO().obtenerClientePorId(clienteId);
                Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(rs.getString("empleado_id"));
                List<Prenda> prendas = obtenerPrendasServicio(rs.getInt("numero"));
                ServicioAlquiler s = new ServicioAlquiler(
                        rs.getDate("fecha_alquiler"),
                        cliente,
                        empleado,
                        prendas
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

// Consulta por fecha
    public List<ServicioAlquiler> obtenerServiciosPorFecha(java.util.Date fecha) {
        List<ServicioAlquiler> lista = new ArrayList<>();
        String sql = "SELECT * FROM servicios WHERE DATE(fecha_alquiler) = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(fecha.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new ClienteDAO().obtenerClientePorId(rs.getString("cliente_id"));
                Empleado empleado = new EmpleadoDAO().obtenerEmpleadoPorId(rs.getString("empleado_id"));
                List<Prenda> prendas = obtenerPrendasServicio(rs.getInt("numero"));
                ServicioAlquiler s = new ServicioAlquiler(
                        rs.getDate("fecha_alquiler"),
                        cliente,
                        empleado,
                        prendas
                );
                lista.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
