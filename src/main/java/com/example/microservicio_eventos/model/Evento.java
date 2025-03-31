package com.example.microservicio_eventos.model;

public class Evento {
    private int id;
    private String nombre;
    private String tipo;
    private String fecha;
    private String lugar;

    public Evento(int id, String nombre, String tipo, String fecha, String lugar) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFecha() { return fecha; }
    public String getLugar() { return lugar; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setLugar(String lugar) { this.lugar = lugar; }
}
