/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.*;
import java.util.*;
import patrones.*;
import patrones.strategy.*;
import patrones.command.*;
import patrones.observer.*;
import patrones.iterator.*;

public class Main {

    public static void main(String[] args) {
        AlquilerFacade facade = new AlquilerFacade();
        NegocioAlquiler negocio = NegocioAlquiler.getInstance();

        
        // PRUEBA STRATEGY
        Cliente cliente = new Cliente("C001", "Ana", "Calle 123", "3001234567", "ana@email.com");
        Empleado empleado = new Empleado("E001", "Carlos", "Calle 456", "3009876543", "Atención al cliente");
        Prenda prenda = new TrajeCaballero("REF001", "Rojo", "MarcaX", "M", 100.0, "Algodón", "Formal");
        Prenda p2 = new VestidoDama("REF002", "Azul", "MarcaY", "L", 120.0, true, "Seda", 90);
        List<Prenda> prendas = Arrays.asList(prenda);

        ServicioAlquiler servicioAlquiler = new ServicioAlquiler(new Date(), cliente, empleado, prendas);
        servicioAlquiler.setEstrategiaPrecio(new PrecioBase());
        System.out.println("Costo BASE: " + servicioAlquiler.calcularCosto(3));

        servicioAlquiler.setEstrategiaPrecio(new PrecioTemporadaAlta());
        System.out.println("Costo TEMPORADA ALTA: " + servicioAlquiler.calcularCosto(3));

        servicioAlquiler.setEstrategiaPrecio(new DescuentoClienteFiel());
        System.out.println("Costo CLIENTE FIEL: " + servicioAlquiler.calcularCosto(3));

        
        // PRUEBA COMMAND
        Command cmd1 = new RegistrarAlquilerCommand(negocio, cliente, empleado, prendas, new Date());
        facade.ejecutarComando(cmd1);

        Command cmd2 = new EnviarLavanderiaCommand(negocio, prenda);
        facade.ejecutarComando(cmd2);

        
        // PRUEBA OBSERVER
        negocio.agregarObserver(new InventarioObserver());
        negocio.agregarObserver(new LavanderiaObserver());

        negocio.enviarALavanderia(p2);

        // PRUEBA ITERATOR
        negocio.registrarPrenda(prenda);
        negocio.registrarPrenda(p2);

        patrones.iterator.Iterator<Prenda> it = negocio.crearIteratorPrendas();
        System.out.println("Recorriendo prendas con Iterator:");
        while (it.hasNext()) {
            Prenda p = it.next();
            System.out.println(" - " + p.getReferencia() + " (" + p.getColor() + ")");
        }

        // PRUEBAS EXISTENTES (consultas con Facade)
        ServicioAlquiler servicio = facade.consultarServicioPorNumero(1);
        if (servicio != null) {
            System.out.println("Servicio #" + servicio.getNumero()
                    + " Cliente: " + servicio.getCliente().getNombre()
                    + " Empleado: " + servicio.getEmpleado().getNombre());
            for (Prenda p : servicio.getPrendas()) {
                System.out.println("   Prenda: " + p.getReferencia() + " - " + p.getClass().getSimpleName());
            }
        } else {
            System.out.println("No se encontró el servicio con ese número.");
        }

        List<ServicioAlquiler> serviciosCliente = facade.consultarServiciosPorCliente("C001");
        System.out.println("Servicios vigentes del cliente Ana:");
        for (ServicioAlquiler s : serviciosCliente) {
            System.out.println("Servicio #" + s.getNumero()
                    + " Fecha: " + s.getFechaAlquiler()
                    + " Empleado: " + s.getEmpleado().getNombre());
            for (Prenda p : s.getPrendas()) {
                System.out.println("   Prenda: " + p.getReferencia() + " - " + p.getClass().getSimpleName());
            }
        }

        Date fechaConsulta = java.sql.Date.valueOf("2026-03-05");
        List<ServicioAlquiler> serviciosFecha = facade.consultarServiciosPorFecha(fechaConsulta);
        System.out.println("Servicios para la fecha " + fechaConsulta + ":");
        for (ServicioAlquiler s : serviciosFecha) {
            System.out.println("Servicio #" + s.getNumero()
                    + " Cliente: " + s.getCliente().getNombre()
                    + " Empleado: " + s.getEmpleado().getNombre());
            for (Prenda p : s.getPrendas()) {
                System.out.println("   Prenda: " + p.getReferencia() + " - " + p.getClass().getSimpleName());
            }
        }

        List<Prenda> prendasTallaM = facade.consultarPrendasPorTalla("M");
        System.out.println("Prendas con talla M:");
        for (Prenda p : prendasTallaM) {
            System.out.println(p.getClass().getSimpleName() + " - " + p.getReferencia() + " (" + p.getColor() + ")");
        }

        List<String> prendasLavanderia = facade.consultarPrendasLavanderia();
        System.out.println("Listado de prendas en lavanderia:");
        for (String p : prendasLavanderia) {
            System.out.println(" - " + p);
        }

        List<String> enviadas = facade.enviarPrendasLavanderia(3);
        System.out.println("Prendas enviadas a lavanderia:");
        for (String p : enviadas) {
            System.out.println(" - " + p);
        }
    }
}
