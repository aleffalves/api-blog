package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.PostDTO;
import com.github.aleffalves.apiblog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor()
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<PostDTO> salvar(@Valid @RequestBody PostDTO postDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.salvar(postDTO));
    }

    @GetMapping()
    public ResponseEntity<Page<PostDTO>> buscarTodosPosts(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                          @RequestParam(value = "limit", defaultValue = "5") Integer limit){
        Pageable pageable = PageRequest.of(page, limit, Sort.by("dataCriacao").descending());
        return ResponseEntity.status(HttpStatus.OK).body(postService.buscarTodosPosts(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        postService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
