package com.luna.Examen01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }

    @GetMapping("/clientes")
    public String clientes() {
        return "clientes";
    }
}