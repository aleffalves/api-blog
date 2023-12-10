package com.github.aleffalves.apiblog.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagemDTO {

    private Integer id;
    private Integer album;
    @NotBlank
    private String nome;
    @NotBlank
    private String extensao;
    @NotBlank
    private String byteImagem;
    private Date dataCriacao;

}
