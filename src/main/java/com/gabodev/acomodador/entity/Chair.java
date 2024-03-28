package com.gabodev.acomodador.entity;

import com.gabodev.acomodador.repository.ChairRepository;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
public class Chair {

    public Chair() {
    }

    public Chair(int row, boolean occupied, int seatNumber) {
        this.row = row;
        this.occupied = occupied;
        this.seatNumber = seatNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int row;
    private boolean occupied;
    private int seatNumber;

}