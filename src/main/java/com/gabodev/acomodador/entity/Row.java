package com.gabodev.acomodador.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
public class Row {

    public Row() {
    }

    public Row(List<Chair> chairs) {
        this.chairs = chairs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ElementCollection
    @CollectionTable(name = "chairs")
    private List<Chair> chairs;

}
