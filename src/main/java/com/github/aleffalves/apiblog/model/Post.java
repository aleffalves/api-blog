package com.github.aleffalves.apiblog.model;

import com.github.aleffalves.apiblog.utils.MethodsUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "mensagem")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "usuario_criacao_id", referencedColumnName = "id")
    private Usuario usuarioCriacao;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;

    @PrePersist()
    public void executarAntesDeSalvar(){
        this.dataCriacao = new Date();
        this.usuarioCriacao = new Usuario(MethodsUtils.getUsuarioLogado().getId());
    }

}
