/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones.command;

/**
 *
 * @author Oscar Danilo Melo
 */
import modelo.Prenda;
import patrones.NegocioAlquiler;

public class EnviarLavanderiaCommand implements Command {
    private NegocioAlquiler negocio;
    private Prenda prenda;

    public EnviarLavanderiaCommand(NegocioAlquiler negocio, Prenda prenda) {
        this.negocio = negocio;
        this.prenda = prenda;
    }

    @Override
    public void ejecutar() {
        negocio.enviarALavanderia(prenda);
    }
}

