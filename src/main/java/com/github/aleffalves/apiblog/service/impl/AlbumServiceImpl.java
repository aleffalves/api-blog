package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.AlbumDTO;
import com.github.aleffalves.apiblog.mapper.AlbumMapper;
import com.github.aleffalves.apiblog.model.Album;
import com.github.aleffalves.apiblog.model.Imagem;
import com.github.aleffalves.apiblog.repository.AlbumRepository;
import com.github.aleffalves.apiblog.service.AlbumService;
import com.github.aleffalves.apiblog.service.ImagemService;
import com.github.aleffalves.apiblog.utils.MethodsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;
    private final ImagemService imagemService;

    @Override
    public void salvar(AlbumDTO albumDTO) {

        try {
            Album album = albumMapper.toEntity(albumDTO);
            album = albumRepository.save(album);

            for(Imagem imagem : album.getImagens()){
                imagem.setAlbum(album);
            }
            imagemService.saveAll(album.getImagens());

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar album.", e);
        }

    }

    @Override
    public Page<AlbumDTO> buscarTodosAlbuns(Pageable pageable) {
        try{
            return albumRepository.findAll(pageable).map(albumMapper::toDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar buscar albuns.", e);
        }
    }

    @Override
    public void deletar(Integer id) {
        Optional<Album> album = albumRepository.findById(id);

        if(album.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Album inexistente.");
        if(!album.get().getUsuarioCriacao().getId().equals(MethodsUtils.getUsuarioLogado().getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não tem permissão para deletar esse album.");
        }

        try {
            albumRepository.deleteById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar deletar album.");
        }
    }
}
