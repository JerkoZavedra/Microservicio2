package com.example.microservicio_eventos.repository;

// -------------------- IMPORTACIONES -------------------------

// Importamos JPA Repository para que Spring genere el acceso a base de datos
import org.springframework.data.jpa.repository.JpaRepository;

// Importamos la entidad Evento
import com.example.microservicio_eventos.model.Evento;

// -------------------- INTERFAZ -------------------------



public interface EventoRepository extends JpaRepository<Evento, Long> {
   
}
