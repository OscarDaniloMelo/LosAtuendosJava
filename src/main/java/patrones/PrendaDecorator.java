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

public abstract class PrendaDecorator extends Prenda {
    protected Prenda prenda;

    public PrendaDecorator(Prenda prenda) {
        super(prenda.getReferencia(), prenda.getColor(), prenda.getMarca(), prenda.getTalla(), prenda.getValorAlquiler());
        this.prenda = prenda;
    }

    @Override
    public abstract double getValorAlquiler();
}

