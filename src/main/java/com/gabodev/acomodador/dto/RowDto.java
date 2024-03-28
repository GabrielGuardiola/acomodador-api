package com.gabodev.acomodador.dto;

import lombok.Data;

import java.util.List;

@Data
public class RowDto {
    private List<ChairDto> chairs;

    public RowDto() {
    }

    public RowDto(List<ChairDto> chairs) {
        this.chairs = chairs;
    }

}