package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostDTO salvar(PostDTO postDTO);

    Page<PostDTO> buscarTodosPosts(Pageable pageable);

    void deletar(Integer id);

}
