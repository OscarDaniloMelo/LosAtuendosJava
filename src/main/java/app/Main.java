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

public class Main {

    public static void main(String[] args) {
        AlquilerFacade facade = new AlquilerFacade();

        // Consulta por número de servicio
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
        List<ServicioAlquiler> serviciosCliente = facade.consultarServiciosPorCliente("C001"); // ID de Ana
        System.out.println("Servicios vigentes del cliente Ana:");
        for (ServicioAlquiler s : serviciosCliente) {
            System.out.println("Servicio #" + s.getNumero()
                    + " Fecha: " + s.getFechaAlquiler()
                    + " Empleado: " + s.getEmpleado().getNombre());
            for (Prenda p : s.getPrendas()) {
                System.out.println("   Prenda: " + p.getReferencia() + " - " + p.getClass().getSimpleName());
            }
        }
        Date fechaConsulta = java.sql.Date.valueOf("2026-03-05"); // ejemplo
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
