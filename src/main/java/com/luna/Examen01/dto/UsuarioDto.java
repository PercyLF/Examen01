package com.luna.Examen01.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
    private int id;
    private String email;
    private boolean activo;
    private List<RolDto> roles;
}
