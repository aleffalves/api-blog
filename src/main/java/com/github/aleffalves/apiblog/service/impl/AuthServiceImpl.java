package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.LoginDTO;
import com.github.aleffalves.apiblog.dto.TokenDTO;
import com.github.aleffalves.apiblog.model.Usuario;
import com.github.aleffalves.apiblog.security.JwtTokenProvider;
import com.github.aleffalves.apiblog.service.AuthService;
import com.github.aleffalves.apiblog.service.UsuarioService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UsuarioService usuarioService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenDTO logar(LoginDTO loginDTO) {

        try {
            Usuario usuario = (Usuario) usuarioService.loadUserByUsername(loginDTO.getEmail());

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha()));
            return jwtTokenProvider.createAccessToken(usuario);

        }catch (Exception e){
            throw new BadCredentialsException("Usuario ou senha inválida.");
        }

    }
}
