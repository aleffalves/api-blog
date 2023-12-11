package com.github.aleffalves.apiblog.mapper;

import com.github.aleffalves.apiblog.dto.AlbumDTO;
import com.github.aleffalves.apiblog.model.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {ImagemMapper.class})
public interface AlbumMapper {

    Album toEntity(AlbumDTO dto);
    AlbumDTO toDTO(Album entity);


    List<Album >toEntity(List<AlbumDTO> dto);
    List<AlbumDTO> toDTO(List<Album> entity);
}
