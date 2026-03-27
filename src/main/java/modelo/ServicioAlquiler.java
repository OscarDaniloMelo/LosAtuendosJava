/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Oscar Danilo Melo
 */
import java.util.Date;
import java.util.List;

// Importamos la interfaz de estrategia
import patrones.strategy.PrecioStrategy;
import patrones.strategy.PrecioBase;

public class ServicioAlquiler {
    private static int consecutivo = 1; // contador global
    private int numero;
    private Date fechaSolicitud;
    private Date fechaAlquiler;
    private Cliente cliente;
    private Empleado empleado;
    private List<Prenda> prendas;

    // NUEVO: estrategia de precio
    private PrecioStrategy estrategiaPrecio;

    // Constructor
    public ServicioAlquiler(Date fechaAlquiler, Cliente cliente, Empleado empleado, List<Prenda> prendas) {
        this.numero = consecutivo++;
        this.fechaSolicitud = new Date();
        this.fechaAlquiler = fechaAlquiler;
        this.cliente = cliente;
        this.empleado = empleado;
        this.prendas = prendas;
        this.estrategiaPrecio = new PrecioBase(); // estrategia por defecto
    }

    // Getters y Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    // NUEVO: asignar estrategia
    public void setEstrategiaPrecio(PrecioStrategy estrategia) {
        this.estrategiaPrecio = estrategia;
    }

    // NUEVO: calcular costo usando la estrategia
    public double calcularCosto(int diasAlquiler) {
        if (estrategiaPrecio == null) {
            estrategiaPrecio = new PrecioBase(); // fallback
        }
        // ejemplo: calcular sobre la primera prenda
        return estrategiaPrecio.calcularPrecio(prendas.get(0), diasAlquiler);
    }
}
