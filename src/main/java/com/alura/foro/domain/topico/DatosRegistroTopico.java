package com.alura.foro.domain.topico;

import com.alura.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank(message = "El titulo no puede estar vacio")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacio")
        String mensaje,
        @NotNull(message = "El status no puede ser nulo")
        StatusTopico status,
        @NotNull(message = "El autor no puede ser nulo")
        Usuario autor,
        @NotBlank(message = "El curso no puede estar vacio")
        String curso
) {
    public LocalDateTime fecha_creacion() {
        return LocalDateTime.now();
    }
}