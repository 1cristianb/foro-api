package com.alura.foro.infra.errores;

public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(String mensaje) {
        super(mensaje);
    }
}