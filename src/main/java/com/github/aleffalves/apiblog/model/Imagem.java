package com.github.aleffalves.apiblog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "imagem")
public class Imagem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "byte_imagem")
    private byte[] byteImagem;

    @Column(name = "tamanho_imagem")
    private String tamanhoImagem;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @PrePersist()
    public void executarAntesDeSalvar(){
        this.dataCriacao = new Date();
    }

}
