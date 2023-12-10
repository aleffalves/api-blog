package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.mapper.PostMapper;
import com.github.aleffalves.apiblog.model.Post;
import com.github.aleffalves.apiblog.repository.PostRepository;
import com.github.aleffalves.apiblog.service.PostService;
import com.github.aleffalves.apiblog.utils.MethodsUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDTO salvar(PostDTO postDTO) {

        try {
            Post post = postRepository.save(postMapper.toEntity(postDTO));
            return postMapper.toDTO(post);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar post.", e);
        }

    }

    @Override
    public Page<PostDTO> buscarTodosPosts(Pageable pageable) {

        try{
            return postRepository.findAll(pageable).map(postMapper::toDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar buscar posts.", e);
        }

    }

    @Override
    public void deletar(Integer id) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post inexistente.");
        if(!post.get().getUsuarioCriacao().getId().equals(MethodsUtils.getUsuarioLogado().getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não tem permissão para deletar esse comentário.");
        }

        try {
            postRepository.deleteById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar deletar comentário.");
        }
    }
}
