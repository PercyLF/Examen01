package com.luna.Examen01.validator;

import com.luna.Examen01.entity.Evento;
import com.luna.Examen01.exception.ValidateException;

public class EventoValidator {
    public static void save(Evento registro) {
        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 255 caracteres");
        }
    }
}
