package com.alura.foro.domain.topico;

import java.time.LocalDateTime;
public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {}
