package com.gabodev.acomodador.service;

import com.gabodev.acomodador.dto.ChairDto;
import com.gabodev.acomodador.entity.Chair;
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
    List<Chair> room = new ArrayList<>();

    public RoomService(ChairRepository chairRepository) {
        this.chairRepository = chairRepository;
    }

    @Override
    public void init() {

        this.generateRoom();
        this.printRoom();

        LogUtils.logInfo("RoomService initialized");
    }

    private void generateRoom() {
        for (int j = 1; j <= 9; j++) {
            for (int i = 9; i > 0; i -= 2) {
                Chair chair = new Chair();
                chair.setRow(j);
                chair.setSeatNumber(i);
                room.add(chair);
                chairRepository.save(chair);
            }
            for (int i = 2; i <= 10; i += 2) {
                Chair chair = new Chair();
                chair.setRow(j);
                chair.setSeatNumber(i);
                chairRepository.save(chair);
            }
        }
    }
    public void printRoom() {
        for (Chair chair : room) {
            for(int i = 1; i <= 9; i++) {
                System.out.print(chair.getSeatNumber() + " ");
            }
            System.out.println();
        }
    }

    public List<ChairDto> getRoom() {
        List<ChairDto> roomDto = new ArrayList<>();
        room = chairRepository.findAll();
        for (Chair chair : room) {
            ChairDto chairDto = new ChairDto(chair.getRow(), chair.isOccupied(), chair.getSeatNumber());
            roomDto.add(chairDto);
        }

        return roomDto;
    }
}
