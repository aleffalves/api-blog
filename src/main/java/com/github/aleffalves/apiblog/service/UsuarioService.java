package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.dto.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {

    UsuarioDTO salvar(UsuarioDTO usuarioDTO);
    UserDetails loadUserByUsername(String email);
}
