/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.strategy;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.Prenda;

public class PrecioBase implements PrecioStrategy {
    @Override
    public double calcularPrecio(Prenda prenda, int dias) {
        return prenda.getValorAlquiler() * dias;
    }
}

