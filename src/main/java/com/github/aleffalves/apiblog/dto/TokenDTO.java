package com.github.aleffalves.apiblog.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class TokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
    private Boolean autenticado;
    private Date criacao;
    private Date expiracao;
    private String accessToken;

    public TokenDTO(String email, Boolean autenticado, Date criacao, Date expiracao, String accessToken) {
        this.email = email;
        this.autenticado = autenticado;
        this.criacao = criacao;
        this.expiracao = expiracao;
        this.accessToken = accessToken;
    }

}
