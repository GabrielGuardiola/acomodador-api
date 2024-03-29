package com.gabodev.acomodador.dto;


import lombok.Data;

@Data
public class ChairDto {

    private int row;
    private boolean occupied;
    private int seatNumber;

    public ChairDto() {
    }

    public ChairDto(int row, boolean occupied, int seatNumber) {
        this.row = row;
        this.occupied = occupied;
        this.seatNumber = seatNumber;
    }
}