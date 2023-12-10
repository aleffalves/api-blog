package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.mapper.PostMapper;
import com.github.aleffalves.apiblog.model.Post;
import com.github.aleffalves.apiblog.repository.PostRepository;
import com.github.aleffalves.apiblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
}
