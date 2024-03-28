package com.gabodev.acomodador.service;

import com.gabodev.acomodador.dto.RowDto;

import java.util.List;

public interface IRoomService {

    void init();

    List<RowDto> getRoom();
    void printRoom();

}
