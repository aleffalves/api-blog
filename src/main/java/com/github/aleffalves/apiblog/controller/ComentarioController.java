package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.ComentarioDTO;
import com.github.aleffalves.apiblog.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @PostMapping()
    public ResponseEntity<ComentarioDTO> salvar(@Valid @RequestBody ComentarioDTO comentarioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioService.salvar(comentarioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        comentarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
