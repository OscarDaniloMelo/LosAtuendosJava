/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.iterator;

/**
 *
 * @author Oscar Danilo Melo
 */
public interface Aggregate<T> {
    Iterator<T> crearIterator();
}

