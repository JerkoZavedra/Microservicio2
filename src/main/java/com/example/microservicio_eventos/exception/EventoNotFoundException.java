package com.example.microservicio_eventos.exception;

// -------------------- IMPORTACIONES -------------------------

// Importa los códigos de estado HTTP que usaremos para asignar un 404
import org.springframework.http.HttpStatus;

// Permite asociar la excepción a un código de estado HTTP automáticamente
import org.springframework.web.bind.annotation.ResponseStatus;

// -------------------- CLASE -------------------------


@ResponseStatus(HttpStatus.NOT_FOUND) // Hace que, al lanzarla, Spring devuelva directamente un 404 Not Found
public class EventoNotFoundException extends RuntimeException {

    /**
     * Constructor de la excepción.
     * Recibe el ID que el usuario buscó y no fue encontrado.
     * 
     * @param id ID del evento que no existe.
     */
    public EventoNotFoundException(Long id) {
        // Generamos un mensaje personalizado que luego será visible en la respuesta de error
        super("El evento con id " + id + " no fue encontrado");
    }
}


