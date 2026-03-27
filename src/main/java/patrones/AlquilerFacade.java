/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.*;
import persistencia.*;
import java.util.*;

// NUEVO: importamos la interfaz Command
import patrones.command.Command;

public class AlquilerFacade {

    private ClienteDAO clienteDAO = new ClienteDAO();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private PrendaDAO prendaDAO = new PrendaDAO();
    private ServicioDAO servicioDAO = new ServicioDAO();
    private LavanderiaDAO lavanderiaDAO = new LavanderiaDAO();

    public void registrarCliente(Cliente c) {
        clienteDAO.insertarCliente(c);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.obtenerClientes();
    }

    public void registrarEmpleado(Empleado e) {
        empleadoDAO.insertarEmpleado(e);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.obtenerEmpleados();
    }

    public void registrarPrenda(Prenda p) {
        prendaDAO.insertarPrenda(p);
    }

    public List<Prenda> listarPrendas() {
        return prendaDAO.obtenerPrendas();
    }

    public void registrarServicio(ServicioAlquiler s) {
        servicioDAO.insertarServicio(s);
    }

    public List<ServicioAlquiler> listarServicios() {
        return servicioDAO.obtenerServicios();
    }

    public ServicioAlquiler consultarServicioPorNumero(int numero) {
        return servicioDAO.obtenerServicioPorNumero(numero);
    }

    public List<ServicioAlquiler> consultarServiciosPorCliente(String clienteId) {
        return servicioDAO.obtenerServiciosPorCliente(clienteId);
    }

    public List<ServicioAlquiler> consultarServiciosPorFecha(Date fecha) {
        return servicioDAO.obtenerServiciosPorFecha(fecha);
    }

    public List<Prenda> consultarPrendasPorTalla(String talla) {
        return prendaDAO.obtenerPrendasPorTalla(talla);
    }

    public void registrarPrendaLavanderia(String referenciaPrenda, boolean prioridad) {
        lavanderiaDAO.registrarPrendaLavanderia(referenciaPrenda, prioridad);
    }

    public List<String> consultarPrendasLavanderia() {
        return lavanderiaDAO.obtenerPrendasLavanderia();
    }

    public List<String> enviarPrendasLavanderia(int cantidad) {
        return lavanderiaDAO.enviarPrendasLavanderia(cantidad);
    }

    // NUEVO: método para ejecutar comandos
    public void ejecutarComando(Command comando) {
        comando.ejecutar();
    }
}

