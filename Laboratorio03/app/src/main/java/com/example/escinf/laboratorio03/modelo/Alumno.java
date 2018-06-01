package com.example.escinf.laboratorio03.modelo;

import java.io.Serializable;

public class Alumno implements Serializable {

    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private String fechaNacimiento;

    public Alumno() {
    }

    public Alumno(String cedula, String nombre, String telefono, String email, String fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + '\n' + "Cedula: " + cedula + '\n' + "Email: " + email;
    }

}
