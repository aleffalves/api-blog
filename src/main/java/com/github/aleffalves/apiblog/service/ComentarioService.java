package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.dto.ComentarioDTO;

public interface ComentarioService {

    ComentarioDTO salvar(ComentarioDTO comentarioDTO);

    void deletar(Integer id);

}
