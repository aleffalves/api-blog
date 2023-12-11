package com.github.aleffalves.apiblog.service.impl;

import com.github.aleffalves.apiblog.dto.UsuarioDTO;
import com.github.aleffalves.apiblog.mapper.UsuarioMapper;
import com.github.aleffalves.apiblog.model.Usuario;
import com.github.aleffalves.apiblog.repository.UsuarioRepository;
import com.github.aleffalves.apiblog.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if(usuario.isPresent()){
            return usuario.get();
        }else {
            throw new UsernameNotFoundException("Email " + username + " não encontrado.");
        }
    }

    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {

        boolean usuarioExistente = usuarioRepository.existsByEmail(usuarioDTO.getEmail());

        if(usuarioExistente) throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuario cadastrado com este email.");

        try {
            usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDTO));
            usuarioDTO = usuarioMapper.toDTO(usuario);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao tentar salvar usuario.", e);
        }
        return usuarioDTO;
    }
}
