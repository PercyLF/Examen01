package com.luna.Examen01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDto {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Double costo;
}
