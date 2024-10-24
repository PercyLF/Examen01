package com.luna.Examen01.service;

import com.luna.Examen01.entity.Evento;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface EventoService {
    public List<Evento> findAll(Pageable page);
    public List<Evento> findAll();
    public List<Evento> finByNombre(String nombre,Pageable page);
    public Evento findById(int id);
    public Evento save(Evento evento);
    public void delete(int id);
}