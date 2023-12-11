package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.model.Imagem;
import java.util.List;

public interface ImagemService {

    void saveAll(List<Imagem> imagens);
}
