package com.gustavoronchi.spring_webflux_mongodb.dto;

import com.gustavoronchi.spring_webflux_mongodb.entities.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDTO {

    private String id;
    private Instant momento;
    private String titulo;
    private String corpo;

    private AutorDTO autor;

    private List<ComentarioDTO> comments = new ArrayList<>();

    public PostDTO() {
    }

    public PostDTO(String id, Instant momento, String titulo, String corpo, String authorId, String authorName,
                   AutorDTO author) {
        this.id = id;
        this.momento = momento;
        this.titulo = titulo;
        this.corpo = corpo;
        autor = new AutorDTO(authorId, authorName);
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.momento = post.getMomento();
        this.titulo = post.getTitulo();
        this.corpo = post.getCorpo();
        autor = new AutorDTO(post.getAutorId(), post.getAutorNome());

        List result = List.copyOf(post.getComentarios());
        comments.addAll(result);
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

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    public List<ComentarioDTO> getComments() {
        return comments;
    }

    public void addComments() {
    }
}
