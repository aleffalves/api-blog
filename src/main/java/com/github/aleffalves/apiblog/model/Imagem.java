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

    @Column(name = "extensao")
    private String extensao;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "byte_imagem")
    private String byteImagem;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @PrePersist()
    public void executarAntesDeSalvar(){
        this.dataCriacao = new Date();
    }

}
