package com.github.aleffalves.apiblog.mapper;

import com.github.aleffalves.apiblog.dto.ComentarioDTO;
import com.github.aleffalves.apiblog.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ComentarioMapper {

    @Mapping(source = "post" , target = "post.id")
    Comentario toEntity(ComentarioDTO dto);
    @Mapping(source = "post.id" , target = "post")
    ComentarioDTO toDTO(Comentario entity);

    List<Comentario> toEntity(List<ComentarioDTO> dto);
    List<ComentarioDTO> toDTO(List<Comentario> entity);

}
