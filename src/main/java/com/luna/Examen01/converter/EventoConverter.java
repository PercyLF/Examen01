package com.luna.Examen01.converter;

import com.luna.Examen01.dto.EventoDto;
import com.luna.Examen01.entity.Evento;
import org.springframework.stereotype.Component;

@Component

public class EventoConverter extends AbstractConverter<Evento, EventoDto>{

    @Override
    public EventoDto fromEntity(Evento entity) {
        if (entity == null) return null;
        /*CategoriaDto dto = new CategoriaDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());*/
        return EventoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .fechaInicio(entity.getFechaInicio())
                .fechaFin(entity.getFechaFin())
                .costo(entity.getCosto())
                .build();
    }

    @Override
    public Evento fromDTO(EventoDto dto) {
        if (dto == null) return null;
        return Evento.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .costo(dto.getCosto())
                .build();
    }
}
