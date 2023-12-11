package com.github.aleffalves.apiblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO {

    private Integer id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String mensagem;
    private UsuarioSelectDTO usuarioCriacao;
    private Date dataCriacao;
    private List<ComentarioDTO> comentarios;

}
