package com.github.aleffalves.apiblog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Column(name = "sobrenome")
    private String sobrenome;

    @NotBlank @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Column(name = "senha")
    private String senha;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = {@JoinColumn (name = "id_usuario")},
            inverseJoinColumns = {@JoinColumn (name = "id_permissao")}
    )
    private List<Permissao> permissoes;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        for (Permissao permissao : permissoes) {
            roles.add(permissao.getDescricao());
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @PrePersist()
    public void executarAntesDeSalvar(){
        this.dataCriacao = new Date();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
