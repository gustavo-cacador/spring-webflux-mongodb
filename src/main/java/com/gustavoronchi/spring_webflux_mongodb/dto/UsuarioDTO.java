package com.gustavoronchi.spring_webflux_mongodb.dto;

import com.gustavoronchi.spring_webflux_mongodb.entities.Usuario;

import java.util.List;

public class UsuarioDTO {

    private String id;
    private String nome;
    private String email;

    private List<PostDTO> posts;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario toEntity(String id, String nome, String email) {
        return new Usuario(id, nome, email);
    }
}
