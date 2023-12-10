package com.github.aleffalves.apiblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.aleffalves.apiblog.model.Imagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumDTO {

    private Integer id;
    @NotBlank
    private String nome;
    private UsuarioSelectDTO usuarioCriacao;
    private Date dataCriacao;
    @NotNull
    private List<ImagemDTO> imagens;
}
