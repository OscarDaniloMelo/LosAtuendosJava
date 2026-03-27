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

// NUEVO: importamos las interfaces Observer y Subject
import patrones.observer.Observer;
import patrones.observer.Subject;

public class NegocioAlquiler implements Subject {

    private static NegocioAlquiler instancia;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Prenda> prendas;
    private List<ServicioAlquiler> servicios;
    private List<Prenda> lavanderia;

    // NUEVO: lista de observadores
    private List<Observer> observers;

    // Constructor privado
    private NegocioAlquiler() {
        clientes = new ArrayList<>();
        empleados = new ArrayList<>();
        prendas = new ArrayList<>();
        servicios = new ArrayList<>();
        lavanderia = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Método estático para obtener la única instancia (Singleton)
    public static NegocioAlquiler getInstance() {
        if (instancia == null) {
            instancia = new NegocioAlquiler();
        }
        return instancia;
    }

    // Métodos de registro
    public void registrarCliente(Cliente c) {
        clientes.add(c);
    }

    public void registrarEmpleado(Empleado e) {
        empleados.add(e);
    }

    public void registrarPrenda(Prenda p) {
        prendas.add(p);
    }

    public void registrarServicio(ServicioAlquiler s) {
        servicios.add(s);
    }

    // Métodos de consulta
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public List<ServicioAlquiler> getServicios() {
        return servicios;
    }

    public List<Prenda> getLavanderia() {
        return lavanderia;
    }

    // NUEVO: registrar un alquiler (usado por RegistrarAlquilerCommand)
    public void registrarAlquiler(Cliente cliente, Empleado empleado, List<Prenda> prendas, Date fecha) {
        ServicioAlquiler servicio = new ServicioAlquiler(fecha, cliente, empleado, prendas);
        servicios.add(servicio);
        System.out.println("Servicio de alquiler registrado: " + servicio.getNumero());
    }

    // NUEVO: enviar prenda a lavandería (usado por EnviarLavanderiaCommand)
    public void enviarALavanderia(Prenda prenda) {
        lavanderia.add(prenda);
        System.out.println("Prenda enviada a lavandería: " + prenda.getReferencia());
        notificarObservers(prenda); // notifica a los observadores
    }

    // Implementación de Subject
    @Override
    public void agregarObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removerObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notificarObservers(Prenda prenda) {
        for (Observer obs : observers) {
            obs.actualizar(prenda);
        }
    }

    // NUEVO: método para crear un iterador de prendas
    public patrones.iterator.Iterator<Prenda> crearIteratorPrendas() {
        return new patrones.iterator.PrendaIterator(prendas);
    }

}
