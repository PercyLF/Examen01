package com.luna.Examen01.service.impl;

import java.util.List;

import com.luna.Examen01.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luna.Examen01.entity.Evento;
import com.luna.Examen01.exception.GeneralException;
import com.luna.Examen01.exception.NoDataFoundException;
import com.luna.Examen01.exception.ValidateException;
import com.luna.Examen01.repository.EventoRepository;
import com.luna.Examen01.service.EventoService;
import com.luna.Examen01.validator.EventoValidator;

@Service
public class EventoServiceImpl implements EventoService {
    @Autowired
    private EventoRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAll(Pageable page) {
        try {
            List<Evento> registros=repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> finByNombre(String nombre, Pageable page) {
        try {
            List<Evento> registros=repository.findByNombreContaining(nombre,page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findById(int id) {
        try {
            Evento registro=repository.findById(id).
                    orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        try {

            EventoValidator.save(evento);
            //Nuevo registro
            if (evento.getId()==0) {
                Evento nuevo=repository.save(evento);
                return nuevo;
            }
            //editar registro
            Evento registro=repository.findById(evento.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
            registro.setNombre(evento.getNombre());
            registro.setDescripcion(evento.getDescripcion());
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Evento registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        try {
            List<Evento> registros=repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        }
        catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
