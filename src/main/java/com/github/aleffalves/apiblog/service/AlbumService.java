package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.dto.AlbumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlbumService {

    void salvar(AlbumDTO albumDTO);

    Page<AlbumDTO> buscarTodosAlbuns(Pageable pageable);

    void deletar(Integer id);

}
