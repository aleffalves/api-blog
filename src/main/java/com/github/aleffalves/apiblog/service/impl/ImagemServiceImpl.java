package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.model.Imagem;
import com.github.aleffalves.apiblog.repository.ImagemRepository;
import com.github.aleffalves.apiblog.service.ImagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemServiceImpl implements ImagemService {

    private final ImagemRepository imagemRepository;

    @Override
    public void saveAll(List<Imagem> imagens) {
        try {
            imagemRepository.saveAll(imagens);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar imagens do album.", e);
        }
    }
}
