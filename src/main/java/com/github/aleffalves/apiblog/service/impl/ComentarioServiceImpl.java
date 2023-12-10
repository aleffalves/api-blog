package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.ComentarioDTO;
import com.github.aleffalves.apiblog.mapper.ComentarioMapper;
import com.github.aleffalves.apiblog.model.Comentario;
import com.github.aleffalves.apiblog.repository.ComentarioRepository;
import com.github.aleffalves.apiblog.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public ComentarioServiceImpl(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    public ComentarioDTO salvar(ComentarioDTO comentarioDTO) {
        try {
            Comentario post = comentarioRepository.save(comentarioMapper.toEntity(comentarioDTO));
            return comentarioMapper.toDTO(post);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar comentario.", e);
        }
    }
}
