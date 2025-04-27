package com.example.microservicio_eventos.service;

import com.example.microservicio_eventos.exception.EventoNotFoundException;
import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.repository.EventoRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public List<Evento> obtenerTodos() {
        log.debug("Servicio: obtenerTodos()");
        return repository.findAll(Sort.by("id").ascending());
    }

    public Evento obtenerPorId(Long id) {
        log.debug("Servicio: obtenerPorId({})", id);
        return repository.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));
    }

    public Evento guardar(Evento evento) {
        log.debug("Servicio: guardar({})", evento.getNombre());

        if (repository.existsById(evento.getId())) {
            log.error("Ya existe un evento con ID {}", evento.getId());
            throw new IllegalArgumentException("Ya existe un evento con ID " + evento.getId());
        }

        return repository.save(evento);
    }

    public Evento actualizar(Long id, Evento datosActualizados) {
        log.debug("Servicio: actualizar({}, {})", id, datosActualizados.getNombre());

        Evento existente = repository.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));

        existente.setNombre(datosActualizados.getNombre());
        existente.setTipo(datosActualizados.getTipo());
        existente.setFecha(datosActualizados.getFecha());
        existente.setLugar(datosActualizados.getLugar());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        log.debug("Servicio: eliminar({})", id);

        Evento existente = repository.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));

        repository.delete(existente);
    }
}
