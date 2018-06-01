package com.example.escinf.laboratorio03.modelo;


import java.io.Serializable;

public class Curso implements Serializable {

    private String codigo;
    private String nombre;
    private int creditos;
    private int horasSemanales;

    public Curso() {
    }

    public Curso(String codigo, String nombre, int creditos, int horasSemanales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Codigo: " + codigo + "\n" +
                "Creditos: " + creditos;
    }

}
