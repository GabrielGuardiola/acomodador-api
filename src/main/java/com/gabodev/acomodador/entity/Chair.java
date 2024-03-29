package com.gabodev.acomodador.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
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

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "occupied")
    private boolean occupied;

}