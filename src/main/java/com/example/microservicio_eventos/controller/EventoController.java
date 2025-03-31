package com.example.microservicio_eventos.controller;

import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.service.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> obtenerTodos() {
        return eventoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Evento obtenerPorId(@PathVariable int id) {
        return eventoService.obtenerPorId(id);
    }
}
