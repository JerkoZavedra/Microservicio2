package com.example.microservicio_eventos;

import com.example.microservicio_eventos.exception.EventoNotFoundException;
import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.repository.EventoRepository;
import com.example.microservicio_eventos.service.EventoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Sort;

public class EventoServiceTest {

    // 🔧 Repositorio simulado
    private EventoRepository eventoRepository;

    // 🎯 Servicio real a probar
    private EventoService eventoService;

    // 🔁 Se ejecuta antes de cada prueba
    @BeforeEach
    public void setUp() {
        eventoRepository = mock(EventoRepository.class);
        eventoService = new EventoService(eventoRepository);
    }

    // ✅ PRUEBA: obtener todos los eventos
    @Test
    public void testObtenerTodos() {
        Evento e1 = new Evento(1L, "Feria de Adopción", "Adopción", "2025-05-10", "Plaza Central");
        Evento e2 = new Evento(2L, "Taller de Cuidados", "Educativo", "2025-06-15", "Centro Cultural");

        when(eventoRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(e1, e2));

        List<Evento> resultado = eventoService.obtenerTodos();

        assertEquals(2, resultado.size());
        assertEquals("Feria de Adopción", resultado.get(0).getNombre());
    }

    // ✅ PRUEBA: obtener por ID cuando existe
    @Test
    public void testObtenerPorId_existente() {
        Evento evento = new Evento(1L, "Expo Mascotas", "Feria", "2025-08-01", "Parque Sur");

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Evento resultado = eventoService.obtenerPorId(1L);

        assertEquals("Expo Mascotas", resultado.getNombre());
        assertEquals("Feria", resultado.getTipo());
    }

    // ❌ PRUEBA: obtener por ID cuando NO existe
    @Test
    public void testObtenerPorId_noExistente() {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EventoNotFoundException.class, () -> {
            eventoService.obtenerPorId(99L);
        });
    }
}


