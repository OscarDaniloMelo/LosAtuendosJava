/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.command;

/**
 *
 * @author Oscar Danilo Melo
 */
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Prenda;
import patrones.NegocioAlquiler;

public class RegistrarAlquilerCommand implements Command {
    private NegocioAlquiler negocio;
    private Cliente cliente;
    private Empleado empleado;
    private List<Prenda> prendas;
    private Date fecha;

    public RegistrarAlquilerCommand(NegocioAlquiler negocio, Cliente cliente, Empleado empleado, List<Prenda> prendas, Date fecha) {
        this.negocio = negocio;
        this.cliente = cliente;
        this.empleado = empleado;
        this.prendas = prendas;
        this.fecha = fecha;
    }

    @Override
    public void ejecutar() {
        negocio.registrarAlquiler(cliente, empleado, prendas, fecha);
    }
}

