package com.gabodev.acomodador.service;

import com.gabodev.acomodador.dto.ChairDto;

import java.util.List;

public interface IRoomService {

    void init();

    List<ChairDto> getRoom();

    List<ChairDto> assignBestSeats(int numberOfTickets);


}
