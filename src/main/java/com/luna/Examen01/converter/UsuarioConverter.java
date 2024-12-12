package com.luna.Examen01.converter;


import com.luna.Examen01.dto.RolDto;
import com.luna.Examen01.dto.UsuarioDto;
import com.luna.Examen01.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDto> {
    @Override
    public UsuarioDto fromEntity(Usuario entity) {
        if(entity == null) return null;

        List<RolDto> rolesDto = entity.getRoles().stream()
                .map(rol -> new RolDto(rol.getId(), rol.getNombre()))
                .collect(Collectors.toList());

        return UsuarioDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .activo(entity.isActivo())
                .roles(rolesDto)
                .build();
    }

    @Override
    public Usuario fromDTO(UsuarioDto dto) {
        if(dto == null) return null;
        return Usuario.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .activo(dto.isActivo())
                .build();
    }
}
