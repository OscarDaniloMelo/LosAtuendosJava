/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.iterator;

/**
 *
 * @author Oscar Danilo Melo
 */
import java.util.List;
import modelo.Prenda;

public class PrendaIterator implements Iterator<Prenda> {
    private List<Prenda> prendas;
    private int posicion = 0;

    public PrendaIterator(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    @Override
    public boolean hasNext() {
        return posicion < prendas.size();
    }

    @Override
    public Prenda next() {
        return prendas.get(posicion++);
    }
}
