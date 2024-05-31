package com.tkosmulski.amore.dto;

import jakarta.persistence.*;

@Entity
public class Trait {
    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Trait() {
    }

    public Trait(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
