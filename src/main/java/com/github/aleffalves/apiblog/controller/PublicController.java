package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.dto.UsuarioDTO;
import com.github.aleffalves.apiblog.service.PostService;
import com.github.aleffalves.apiblog.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final UsuarioService usuarioService;
    private final PostService postService;

    public PublicController(UsuarioService usuarioService, PostService postService) {
        this.usuarioService = usuarioService;
        this.postService = postService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO>salvar(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioDTO));
    }

    @GetMapping("/post")
    public ResponseEntity<Page<PostDTO>> buscarTodosPosts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "limit", defaultValue = "5") Integer limit){
        Pageable pageable = PageRequest.of(page, limit, Sort.by("dataCriacao").descending());
        return ResponseEntity.status(HttpStatus.OK).body(postService.buscarTodosPosts(pageable));
    }

}
