package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<PostDTO> salvar(@Valid @RequestBody PostDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.salvar(postDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        postService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
