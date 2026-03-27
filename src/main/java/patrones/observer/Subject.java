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

public interface Subject {
    void agregarObserver(Observer obs);
    void removerObserver(Observer obs);
    void notificarObservers(Prenda prenda);
}

