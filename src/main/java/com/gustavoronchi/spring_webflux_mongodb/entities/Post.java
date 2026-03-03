package com.gustavoronchi.spring_webflux_mongodb.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "postagens")
public class Post {

    @Id
    private String id;
    private Instant momento;
    private String titulo;
    private String corpo;

    private Autor autor;

    // relacionamento entre post e usuario
    @DocumentReference
    private Usuario usuario;

    private List<Comentario> comentarios = new ArrayList<>();

    public Post() {}

    public Post(String id, Instant momento, String titulo, String corpo, Autor autor) {
        this.id = id;
        this.momento = momento;
        this.titulo = titulo;
        this.corpo = corpo;
        this.autor = autor;
    }

    public Post(String id, Instant momento, String titulo, String corpo, String autorId, String autorNome) {
        this.id = id;
        this.momento = momento;
        this.titulo = titulo;
        this.corpo = corpo;
        this.autor = new Autor(autorId, autorNome);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getAutorId() {
        return autor.getId();
    }

    public String getAutorNome() {
        return autor.getNome();
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void addComment(String CommentText, Instant dataComentario, String autorId, String autorNome) {
        Comentario comentario = new Comentario(CommentText, dataComentario, autorId, autorNome);
        comentarios.add(comentario);
    }
}
