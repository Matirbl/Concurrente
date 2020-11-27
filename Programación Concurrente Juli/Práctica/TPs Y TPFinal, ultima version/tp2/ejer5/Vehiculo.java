/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer5;

/**
 *
 * @author jdominguez
 */
public class Vehiculo {
    protected String patente;
    protected String Modelo;
    protected String Marca;
    protected int kmFaltantesParaElService;

    public Vehiculo(String patente, String Modelo, String Marca, int kmFaltantesParaElService) {
        this.patente = patente;
        this.Modelo = Modelo;
        this.Marca = Marca;
        this.kmFaltantesParaElService = kmFaltantesParaElService;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getKmFaltantesParaElService() {
        return kmFaltantesParaElService;
    }

    public void setKmFaltantesParaElService(int kmFaltantesParaElService) {
        this.kmFaltantesParaElService = kmFaltantesParaElService;
    }
        
}
