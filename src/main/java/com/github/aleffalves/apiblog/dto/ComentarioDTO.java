package com.github.aleffalves.apiblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComentarioDTO {

    private Integer id;
    @NotNull
    private Integer post;
    @NotBlank
    private String mensagem;
    private UsuarioSelectDTO usuarioCriacao;
    private Date dataCriacao;

}
