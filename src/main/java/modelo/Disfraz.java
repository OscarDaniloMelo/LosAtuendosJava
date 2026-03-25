/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Oscar Danilo Melo
 */
public class Disfraz extends Prenda {
    private String nombreDisfraz;
    
    //Constructor

    public Disfraz(String referencia, String color, String marca, String talla, double valorAlquiler, String nombreDisfraz) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.nombreDisfraz = nombreDisfraz;
    }
    
    //Getters y Setters

    public String getNombreDisfraz() {
        return nombreDisfraz;
    }

    public void setNombreDisfraz(String nombreDisfraz) {
        this.nombreDisfraz = nombreDisfraz;
    }
    
}

