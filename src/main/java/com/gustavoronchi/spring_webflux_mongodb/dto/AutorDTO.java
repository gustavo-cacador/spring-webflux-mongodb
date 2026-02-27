package com.gustavoronchi.spring_webflux_mongodb.dto;

public class AutorDTO {

    private String id;
    private String name;

    public AutorDTO() {
    }

    public AutorDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
