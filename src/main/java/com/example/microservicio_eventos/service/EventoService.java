package com.example.microservicio_eventos.service;

import com.example.microservicio_eventos.model.Evento;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EventoService {
    private List<Evento> eventos = new ArrayList<>(List.of(
        new Evento(1, "Feria de Adopción", "Feria", "04-10-2025", "Parque Central"),
        new Evento(2, "Concurso de Disfraces", "Competencia", "05-06-2025", "Gimnasio Regional"),
        new Evento(3, "Expo Mascotas", "Exposición", "27-09-2025", "Estadio Municipal")
    ));

    public List<Evento> obtenerTodos() {
        return eventos;
    }

    public Evento obtenerPorId(int id) {
        return eventos.stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);
    }
}