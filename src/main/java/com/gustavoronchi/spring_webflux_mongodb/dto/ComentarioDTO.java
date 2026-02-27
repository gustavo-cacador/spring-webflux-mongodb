package com.gustavoronchi.spring_webflux_mongodb.dto;

import java.time.Instant;

public class ComentarioDTO {

    private String text;
    private Instant date;
    private AutorDTO autor;

    public ComentarioDTO() {
    }

    public ComentarioDTO(String text, Instant date, AutorDTO autor) {
        this.text = text;
        this.date = date;
        this.autor = autor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public AutorDTO getAutor() {
        return autor;
    }

    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }
}
