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
import java.util.*;

public class NegocioAlquiler {
    private static NegocioAlquiler instancia;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Prenda> prendas;
    private List<ServicioAlquiler> servicios;

    // Constructor privado
    private NegocioAlquiler() {
        clientes = new ArrayList<>();
        empleados = new ArrayList<>();
        prendas = new ArrayList<>();
        servicios = new ArrayList<>();
    }

    // Método estático para obtener la única instancia
    public static NegocioAlquiler getInstance() {
        if (instancia == null) {
            instancia = new NegocioAlquiler();
        }
        return instancia;
    }

    // Métodos de registro
    public void registrarCliente(Cliente c) { clientes.add(c); }
    public void registrarEmpleado(Empleado e) { empleados.add(e); }
    public void registrarPrenda(Prenda p) { prendas.add(p); }
    public void registrarServicio(ServicioAlquiler s) { servicios.add(s); }

    // Métodos de consulta
    public List<Cliente> getClientes() { return clientes; }
    public List<Empleado> getEmpleados() { return empleados; }
    public List<Prenda> getPrendas() { return prendas; }
    public List<ServicioAlquiler> getServicios() { return servicios; }
}

