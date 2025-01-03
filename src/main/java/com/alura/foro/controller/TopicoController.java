package com.alura.foro.controller;

import com.alura.foro.domain.ValidacionException;
import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.usuario.Usuario;
import com.alura.foro.domain.usuario.UsuarioRepository;
import com.alura.foro.infra.errores.TopicoNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {

        //Se verifica si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con este título y mensaje.");
        }

        // Se crea y guarda el tópico
        Topico topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        //DTO para la respuesta
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getUsername(),
                topico.getCurso()
        );
        // Se genera la URL del recurso creado
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 20) Pageable paginacion) {
        List<DatosListadoTopico> topicos = topicoRepository.findAll(paginacion)
                .map(DatosListadoTopico::new)
                .getContent();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerDetalleTopico(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ID es requerido");
        }

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico no encontrado con ID: " + id));
        DatosListadoTopico datosDetalleTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(datosDetalleTopico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> actualizarTopico(
            @PathVariable Long id,
            @Valid @RequestBody DatosActualizarTopico datosActualizarTopico) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new TopicoNotFoundException("Tópico no encontrado con ID: " + id);
        }
        Topico topico = topicoOptional.get();

        if (topicoRepository.existsByTituloAndMensajeAndIdNot(
                datosActualizarTopico.titulo(),
                datosActualizarTopico.mensaje(),
                id)) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje");
        }

        topico.setTitulo(datosActualizarTopico.titulo());
        topico.setMensaje(datosActualizarTopico.mensaje());
        topico.setStatus(datosActualizarTopico.status());
        topico.setAutor(datosActualizarTopico.autor());
        topico.setCurso(datosActualizarTopico.curso());

        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tópico no encontrado con ID: " + id);
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.ok("Tópico eliminado con éxito con ID: " + id);
    }
}