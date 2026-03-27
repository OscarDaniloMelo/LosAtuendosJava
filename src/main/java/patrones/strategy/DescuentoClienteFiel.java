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

public class DescuentoClienteFiel implements PrecioStrategy {
    @Override
    public double calcularPrecio(Prenda prenda, int dias) {
        // Aplica un descuento del 20% a clientes fieles
        return prenda.getValorAlquiler() * dias * 0.8;
    }
}

