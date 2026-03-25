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

public class PrendaFactory {

    public static Prenda crearPrenda(String tipo, String referencia, String color, String marca, String talla,
            double valorAlquiler, Object... extras) {
        switch (tipo.toLowerCase()) {
            case "vestido":
                return new VestidoDama(referencia, color, marca, talla, valorAlquiler,
                        (boolean) extras[0], (String) extras[1], (int) extras[2]);
            case "traje":
                return new TrajeCaballero(referencia, color, marca, talla, valorAlquiler,
                        (String) extras[0], (String) extras[1]);
            case "disfraz":
                return new Disfraz(referencia, color, marca, talla, valorAlquiler,
                        (String) extras[0]);
            default:
                throw new IllegalArgumentException("Tipo de prenda no válido: " + tipo);
        }
    }
}
