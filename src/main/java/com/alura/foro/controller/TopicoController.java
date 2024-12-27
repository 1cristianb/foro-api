package com.alura.foro.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @GetMapping
    public String helloWord(){
        return "Hola Mundo";
    }
}