/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.Prenda;

public class LavadoEspecial extends PrendaDecorator {
    private double costoExtra;

    public LavadoEspecial(Prenda prenda, double costoExtra) {
        super(prenda);
        this.costoExtra = costoExtra;
    }

    @Override
    public double getValorAlquiler() {
        return prenda.getValorAlquiler() + costoExtra;
    }
}

