package com.example.microservicio_eventos.controller;

import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.model.ResponseWrapper;
import com.example.microservicio_eventos.service.EventoService;
import com.example.microservicio_eventos.hateoas.EventoModelAssembler;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final EventoModelAssembler assembler;

    public EventoController(EventoService eventoService, EventoModelAssembler assembler) {
        this.eventoService = eventoService;
        this.assembler = assembler;
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

        List<EntityModel<Evento>> eventosModel = eventos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        eventosModel.size(),
                        eventosModel));
    }

    @GetMapping("/{id}")
    public EntityModel<Evento> obtenerPorId(@PathVariable Long id) {
        log.info("GET /eventos/{} - Buscando evento por ID", id);
        Evento evento = eventoService.obtenerPorId(id);
        return assembler.toModel(evento);
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<EntityModel<Evento>>> crearEvento(@Valid @RequestBody Evento evento) {
        log.info("POST /eventos - Creando evento: {}", evento.getNombre());
        Evento creado = eventoService.guardar(evento);
        EntityModel<Evento> creadoModel = assembler.toModel(creado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                        "Evento creado exitosamente",
                        1,
                        List.of(creadoModel)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<EntityModel<Evento>>> actualizarEvento(@PathVariable Long id,
            @Valid @RequestBody Evento eventoActualizado) {
        log.info("PUT /eventos/{} - Actualizando evento", id);
        Evento actualizado = eventoService.actualizar(id, eventoActualizado);
        EntityModel<Evento> actualizadoModel = assembler.toModel(actualizado);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "Evento actualizado exitosamente",
                        1,
                        List.of(actualizadoModel)));
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


