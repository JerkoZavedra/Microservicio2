package com.example.microservicio_eventos.model;

// -------------------- IMPORTACIONES -------------------------

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;


@Data // Lombok: getters, setters, toString(), equals(), hashCode
@AllArgsConstructor // Lombok: constructor con todos los atributos
@NoArgsConstructor  // Lombok: constructor vacío
@Entity // 🔵 Entidad de base de datos
@Table(name = "eventos") // 🔵 Tabla mapeada "eventos"
public class Evento {

    @Id // 🔵 Primary Key
    @NotNull(message = "El ID no puede ser nulo")
    @Positive(message = "El ID debe ser un número positivo")
    private Long id;

    @NotBlank(message = "El nombre del evento no puede estar vacío")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El tipo de evento no puede estar vacío")
    @Size(min = 3, max = 50, message = "El tipo debe tener entre 3 y 50 caracteres")
    private String tipo;

    @NotBlank(message = "La fecha del evento no puede estar vacía")
    @Size(min = 10, max = 10, message = "La fecha debe tener el formato YYYY-MM-DD")
    private String fecha;

    @NotBlank(message = "El lugar del evento no puede estar vacío")
    @Size(min = 3, max = 100, message = "El lugar debe tener entre 3 y 100 caracteres")
    private String lugar;
}

