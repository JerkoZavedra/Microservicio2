package com.example.microservicio_eventos.controller;

import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.model.ResponseWrapper;
import com.example.microservicio_eventos.service.EventoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        log.info("GET /eventos - Obteniendo todos los eventos");

        List<Evento> eventos = eventoService.obtenerTodos();

        if (eventos.isEmpty()) {
            log.warn("No hay eventos registrados actualmente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay eventos registrados actualmente");
        }

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        eventos.size(),
                        eventos));
    }

    @GetMapping("/{id}")
    public Evento obtenerPorId(@PathVariable Long id) {
        log.info("GET /eventos/{} - Buscando evento por ID", id);
        return eventoService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Evento>> crearEvento(@Valid @RequestBody Evento evento) {
        log.info("POST /eventos - Creando evento: {}", evento.getNombre());
        Evento creado = eventoService.guardar(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Evento creado exitosamente",
                        1,
                        List.of(creado)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Evento>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoActualizado) {
        log.info("PUT /eventos/{} - Actualizando evento", id);

        Evento actualizado = eventoService.actualizar(id, eventoActualizado);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento actualizado exitosamente",
                        1,
                        List.of(actualizado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> eliminarEvento(@PathVariable Long id) {
        log.warn("DELETE /eventos/{} - Eliminando evento", id);

        eventoService.eliminar(id);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento eliminado exitosamente",
                        0,
                        null));
    }
}

