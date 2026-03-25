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
import java.util.ArrayList;
import java.util.List;

public class ConjuntoPrendas extends Prenda {
    private List<Prenda> prendas;

    public ConjuntoPrendas(String referencia, String color, String marca, String talla, double valorAlquiler) {
        super(referencia, color, marca, talla, valorAlquiler);
        prendas = new ArrayList<>();
    }

    public void agregarPrenda(Prenda p) {
        prendas.add(p);
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    @Override
    public double getValorAlquiler() {
        double total = valorAlquiler;
        for (Prenda p : prendas) {
            total += p.getValorAlquiler();
        }
        return total;
    }
}

