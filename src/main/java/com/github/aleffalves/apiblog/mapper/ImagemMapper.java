package com.github.aleffalves.apiblog.mapper;

import com.github.aleffalves.apiblog.dto.ImagemDTO;
import com.github.aleffalves.apiblog.model.Imagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ImagemMapper {

    @Mapping(source = "album", target = "album.id")
    Imagem toEntity(ImagemDTO dto);
    @Mapping(source = "album.id", target = "album")
    ImagemDTO toDTO(Imagem entity);

    List<Imagem> toEntity(List<ImagemDTO> dto);
    List<ImagemDTO> toDTO(List<Imagem> entity);
}
