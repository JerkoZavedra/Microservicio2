package com.example.microservicio_eventos;

import com.example.microservicio_eventos.model.Evento;
import com.example.microservicio_eventos.repository.EventoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    public void testGuardarYBuscar() {
        // 🐾 Creamos un evento de prueba
        Evento evento = new Evento(
                5L,
                "Expo Mascotas",
                "Feria",
                "2025-10-01",
                "Centro de Eventos Alameda"
        );

        // 💾 Guardamos el evento en la base de datos embebida
        eventoRepository.save(evento);

        // 🔍 Intentamos buscarlo por ID
        Optional<Evento> encontrado = eventoRepository.findById(5L);

        // ✅ Verificamos que fue encontrado
        assertTrue(encontrado.isPresent());

        // 🧪 Verificamos que el nombre coincida
        assertEquals("Expo Mascotas", encontrado.get().getNombre());
    }
}
