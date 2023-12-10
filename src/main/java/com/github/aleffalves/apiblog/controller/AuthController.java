package com.github.aleffalves.apiblog.controller;

import com.github.aleffalves.apiblog.dto.LoginDTO;
import com.github.aleffalves.apiblog.dto.TokenDTO;
import com.github.aleffalves.apiblog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/logar")
    public ResponseEntity<TokenDTO> logar(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authService.logar(loginDTO));
    }

}
