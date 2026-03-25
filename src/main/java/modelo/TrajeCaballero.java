/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Oscar Danilo Melo
 */
public class TrajeCaballero extends Prenda {
    private String tipo; // convencional, frac, sacoleva, otro
    private String accesorio; // corbata, corbatín, plastrón
    
    //Constructor

    public TrajeCaballero(String referencia, String color, String marca, String talla, double valorAlquiler, String tipo, String accesorio) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.tipo = tipo;
        this.accesorio = accesorio;
    }
    
    //Getters y Setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(String accesorio) {
        this.accesorio = accesorio;
    }

}
