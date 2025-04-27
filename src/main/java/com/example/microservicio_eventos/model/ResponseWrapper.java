package com.example.microservicio_eventos.model;

// -------------------- IMPORTACIONES -------------------------

// Importamos una anotación que nos permite definir en qué orden queremos que aparezcan los atributos en el JSON de salida
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Importamos para poder usar fechas y horas en Java
import java.time.LocalDateTime;

// Importamos la clase List, necesaria para manejar listas de cualquier tipo
import java.util.List;

// -------------------- CLASE -------------------------


@JsonPropertyOrder({ "status", "cantidad", "timestamp", "data" }) 
public class ResponseWrapper<T> {

    // -------------------- ATRIBUTOS -------------------------

    // Indica si la operación fue exitosa u otro estado (por ejemplo: OK, ERROR)
    private String status;

    // Cantidad de elementos devueltos en la lista de datos
    private int cantidad;

    // Fecha y hora exacta en la que se genera la respuesta
    private LocalDateTime timestamp;

    // Lista de datos devueltos (eventos u otros objetos)
    private List<T> data;

    // -------------------- CONSTRUCTOR -------------------------


    public ResponseWrapper(String status, int cantidad, List<T> data) {
        this.status = status;
        this.cantidad = cantidad;
        this.timestamp = LocalDateTime.now(); // Se asigna automáticamente al momento de crear la respuesta
        this.data = data;
    }

    // -------------------- GETTERS y SETTERS -------------------------

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
