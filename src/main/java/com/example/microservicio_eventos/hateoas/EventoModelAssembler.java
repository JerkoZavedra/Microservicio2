package com.example.microservicio_eventos.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.microservicio_eventos.controller.EventoController;
import com.example.microservicio_eventos.model.Evento;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Este ensamblador convierte una entidad Evento en un EntityModel<Evento>
 * con enlaces HATEOAS para enriquecer las respuestas REST.
 */
@Component
public class EventoModelAssembler implements RepresentationModelAssembler<Evento, EntityModel<Evento>> {

    @Override
    public EntityModel<Evento> toModel(Evento evento) {
        return EntityModel.of(
                evento,

                // Enlace al detalle del evento (GET /eventos/{id})
                linkTo(methodOn(EventoController.class)
                        .obtenerPorId(evento.getId()))
                        .withSelfRel(),

                // Enlace para eliminar el evento (DELETE /eventos/{id})
                linkTo(methodOn(EventoController.class)
                        .eliminarEvento(evento.getId()))
                        .withRel("delete"),

                // Enlace para actualizar el evento (PUT /eventos/{id})
                linkTo(methodOn(EventoController.class)
                        .actualizarEvento(evento.getId(), null))
                        .withRel("update"),

                // Enlace para ver todos los eventos (GET /eventos)
                linkTo(methodOn(EventoController.class)
                        .obtenerTodos())
                        .withRel("all")
        );
    }
}

