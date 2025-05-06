package com.example.microservicio_eventos;

import com.example.microservicio_eventos.controller.EventoController;
import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.service.EventoService;
import com.example.microservicio_eventos.hateoas.EventoModelAssembler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@WebMvcTest(EventoController.class) // ✅ Solo testea la capa del controlador
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc; // 🚀 Herramienta para simular solicitudes HTTP

    @MockBean
    private EventoService eventoService; // 🎭 Simulamos el servicio

    @MockBean
    private EventoModelAssembler eventoAssembler; // 🔗 Simulamos el ensamblador HATEOAS

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" }) // 🔐 Simula autenticación
    public void testObtenerPorId() throws Exception {
        // 🐾 Simulamos un evento
        Evento evento = new Evento(1L, "Feria de Adopción", "Adopción", "2025-05-10", "Parque Central");

        // 🔗 Enlaces HATEOAS simulados
        EntityModel<Evento> eventoModel = EntityModel.of(evento,
                linkTo(methodOn(EventoController.class).obtenerPorId(1L)).withSelfRel(),
                linkTo(methodOn(EventoController.class).eliminarEvento(1L)).withRel("delete"),
                linkTo(methodOn(EventoController.class).actualizarEvento(1L, null)).withRel("update"),
                linkTo(methodOn(EventoController.class).obtenerTodos()).withRel("all"));

        // 🎭 Simulamos respuestas del servicio y el assembler
        when(eventoService.obtenerPorId(1L)).thenReturn(evento);
        when(eventoAssembler.toModel(evento)).thenReturn(eventoModel);

        // 🚀 Ejecutamos solicitud GET y validamos
        mockMvc.perform(get("/eventos/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // ✅ HTTP 200
                .andExpect(jsonPath("$.id").value(1)) // 🧪 Validar ID
                .andExpect(jsonPath("$.nombre").value("Feria de Adopción")) // 🧪 Validar nombre
                .andExpect(jsonPath("$.tipo").value("Adopción")) // 🧪 Validar tipo
                .andExpect(jsonPath("$.fecha").value("2025-05-10")) // 🧪 Validar fecha
                .andExpect(jsonPath("$.lugar").value("Parque Central")) // 🧪 Validar lugar
                .andExpect(jsonPath("$._links.self.href").exists()) // 🧪 Enlace self
                .andExpect(jsonPath("$._links.delete.href").exists()) // 🧪 Enlace delete
                .andExpect(jsonPath("$._links.update.href").exists()); // 🧪 Enlace update
    }
}

