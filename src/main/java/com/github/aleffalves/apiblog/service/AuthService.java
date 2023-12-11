package com.github.aleffalves.apiblog.service;

import com.github.aleffalves.apiblog.dto.LoginDTO;
import com.github.aleffalves.apiblog.dto.TokenDTO;

public interface AuthService {
    TokenDTO logar(LoginDTO loginDTO);

}
