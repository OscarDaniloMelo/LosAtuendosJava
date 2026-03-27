/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.observer;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.Prenda;

public class InventarioObserver implements Observer {
    @Override
    public void actualizar(Prenda prenda) {
        System.out.println("Inventario actualizado: " + prenda.getReferencia());
    }
}

