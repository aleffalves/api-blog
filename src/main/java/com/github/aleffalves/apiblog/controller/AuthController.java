package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.LoginDTO;
import com.github.aleffalves.apiblog.dto.TokenDTO;
import com.github.aleffalves.apiblog.dto.UsuarioDTO;
import com.github.aleffalves.apiblog.service.AuthService;
import com.github.aleffalves.apiblog.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor()
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/autenticar")
    public ResponseEntity<TokenDTO> logar(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.logar(loginDTO));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(usuarioDTO));
    }

}
