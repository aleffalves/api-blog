package com.github.aleffalves.apiblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComentarioDTO {

    private Integer id;
    private Integer post;
    private String mensagem;
    private UsuarioSelectDTO usuarioCriacao;
    private Date dataCriacao;

}
