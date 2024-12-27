package com.alura.foro.domain;

public class ValidacionException extends  RuntimeException {
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
}