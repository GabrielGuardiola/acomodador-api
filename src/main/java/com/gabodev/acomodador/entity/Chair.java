package com.gabodev.acomodador.entity;

import com.gabodev.acomodador.repository.ChairRepository;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "chair")
@Table(name = "CHAIRS")
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

    @Column(name = "row_number")
    private int row;
    @Column(name = "occupied")
    private boolean occupied;
    @Column(name = "seat_number")
    private int seatNumber;

}