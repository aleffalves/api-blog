package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.AlbumDTO;
import com.github.aleffalves.apiblog.service.AlbumService;
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
@RequestMapping("/api/album")
@RequiredArgsConstructor()
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping()
    public ResponseEntity<Void> salvar(@Valid @RequestBody AlbumDTO albumDTO){
        albumService.salvar(albumDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Page<AlbumDTO>> buscarTodosAlbuns(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                            @RequestParam(value = "limit", defaultValue = "5") Integer limit){
        Pageable pageable = PageRequest.of(page, limit, Sort.by("dataCriacao").descending());
        return ResponseEntity.status(HttpStatus.OK).body(albumService.buscarTodosAlbuns(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id){
        albumService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
