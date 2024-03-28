package com.gabodev.acomodador.service;

import com.gabodev.acomodador.dto.ChairDto;
import com.gabodev.acomodador.dto.RowDto;
import com.gabodev.acomodador.entity.Chair;
import com.gabodev.acomodador.entity.Row;
import com.gabodev.acomodador.repository.ChairRepository;
import com.gabodev.acomodador.util.LogUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService{

    DozerBeanMapper mapper = new DozerBeanMapper();

    private final ChairRepository chairRepository;
    List<Row> room = new ArrayList<>();
    List<Chair> chairs = new ArrayList<>();

    public RoomService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    @Override
    public void init() {

        this.generateRoomMatrix();
        this.printRoom();

        LogUtils.logInfo("RoomService initialized");
    }

    private void generateRoomMatrix() {
        for (int j = 1; j <= 9; j++) {
            for (int i = 9; i > 0; i -= 2) {
                chairs.add(new Chair(j, false, i));
            }
            for (int i = 2; i <= 10; i += 2) {
                chairs.add(new Chair(j, false, i));
            }

            room.add(new Row(chairs));
            chairs = new ArrayList<>();
        }
    }

    public void printRoom() {
        for (Row row : room) {
            for (Chair chair : row.getChairs()) {
                System.out.print(chair.getSeatNumber() + " ");
            }
            System.out.println();
        }
    }

    private void storeRows() {
        for (Row row : room) {
            for (Chair chair : row.getChairs()) {
                chairRepository.save(chair);
            }
        }
    }

    public List<RowDto> getRoom() {
        List<RowDto> roomDto = new ArrayList<>();
        for (Row row : room) {
            List<ChairDto> chairsDto = new ArrayList<>();
            for (Chair chair : row.getChairs()) {
                chairsDto.add(mapper.map(chair, ChairDto.class));
            }
            roomDto.add(new RowDto(chairsDto));
        }
        return roomDto;
    }



}
