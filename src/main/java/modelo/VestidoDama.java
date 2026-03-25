/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Oscar Danilo Melo
 */
public class VestidoDama extends Prenda {
    private boolean decoraciones;
    private String largoCorto; // "largo" o "corto"
    private int piezas;
    
    //Constructor

    public VestidoDama(String referencia, String color, String marca, String talla, double valorAlquiler, boolean decoraciones, String largoCorto, int piezas) {
        super(referencia, color, marca, talla, valorAlquiler);
        this.decoraciones = decoraciones;
        this.largoCorto = largoCorto;
        this.piezas = piezas;
    }
    
    //getters y setters

    public boolean isPedreria() {
        return decoraciones;
    }

    public void setPedreria(boolean decoraciones) {
        this.decoraciones = decoraciones;
    }

    public String getLargoCorto() {
        return largoCorto;
    }

    public void setLargoCorto(String largoCorto) {
        this.largoCorto = largoCorto;
    }

    public int getPiezas() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

}
