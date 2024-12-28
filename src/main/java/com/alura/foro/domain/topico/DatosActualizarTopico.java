package com.alura.foro.domain.topico;

import com.alura.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull StatusTopico status,
        @NotNull Usuario autor,
        @NotBlank String curso) {
}