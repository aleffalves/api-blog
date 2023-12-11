package com.github.aleffalves.apiblog.mapper;

import com.github.aleffalves.apiblog.dto.UsuarioDTO;
import com.github.aleffalves.apiblog.model.Usuario;
import org.mapstruct.Mapper;

@Mapper
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
    UsuarioDTO toDTO(Usuario entity);

}
