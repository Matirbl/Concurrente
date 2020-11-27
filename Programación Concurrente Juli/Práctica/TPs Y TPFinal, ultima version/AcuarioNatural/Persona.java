/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AcuarioNatural;

/**
 *
 * @author Domínguez Julián, FaI - 974
 */
public class Persona implements Runnable {

    private int idPersona;
    private AcuarioNatural acuario;
    private boolean puedeAlmorzar;
    private boolean puedeMerendar;
    private int asignacionDelGomon;

    public Persona(int id, AcuarioNatural recursoCompartido) {
        idPersona = id;
        acuario = recursoCompartido;
        puedeMerendar = false;
        puedeAlmorzar = false;
    }

    @Override
    public void run() {
        Restaurante restaurante;
        acuario.irAlParque(this);
        acuario.ingresar(this);
        acuario.realizarActividades(this);
        acuario.salir(this);

    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public boolean puedeAlmorzar() {
        return puedeAlmorzar;
    }

    public void setPuedeAlmorzar(boolean puedeAlmorzar) {
        this.puedeAlmorzar = puedeAlmorzar;
    }

    public boolean puedeMerendar() {
        return puedeMerendar;
    }

    public void setPuedeMerendar(boolean puedeMerendar) {
        this.puedeMerendar = puedeMerendar;
    }

    public int getAsignacionDelGomon() {
        return asignacionDelGomon;
    }

    public void setAsignacionDelGomon(int asignacionDelGomon) {
        /*
        cuando se sube se le asigna el correspondiente
         */
        this.asignacionDelGomon = asignacionDelGomon;
    }


}
