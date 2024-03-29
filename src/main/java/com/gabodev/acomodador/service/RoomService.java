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

    public List<ChairDto> getRoom() {
        List<ChairDto> roomDto = new ArrayList<>();
        room = chairRepository.findAll();
        for (Chair chair : room) {
            ChairDto chairDto = new ChairDto(chair.getRow(), chair.isOccupied(), chair.getSeatNumber());
            roomDto.add(chairDto);
        }

        return roomDto;
    }

    public List<ChairDto> assignBestSeats(int numberOfTickets) {
        List<Chair> chairs = chairRepository.findAll();
        List<List<Chair>> roomMatrix = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            List<Chair> row = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                row.add(chairs.get(i * 10 + j));
            }
            roomMatrix.add(row);
        }
        List<Chair> bestSeats = findBestSeats(roomMatrix, numberOfTickets);
        List<ChairDto> bestSeatsDto = new ArrayList<>();
        for (Chair chair : bestSeats) {
            markAsOccupied(chair);
            bestSeatsDto.add(mapper.map(chair, ChairDto.class));
        }
        return bestSeatsDto;

    }

    private void markAsOccupied(Chair chair) {
        chair.setOccupied(true);
        chairRepository.save(chair);
    }

    private List<Chair> findBestSeats(List<List<Chair>> roomMatrix, int numberOfTickets) {
        List<Chair> bestSeats = new ArrayList<>();
        int bestRow = 0;
        int bestColumn = 0;
        int bestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < roomMatrix.size(); i++) {
            List<Chair> row = roomMatrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                Chair chair = row.get(j);
                if (!chair.isOccupied()) {
                    int distance = Math.abs(5 - j) + Math.abs(5 - i);
                    if (distance < bestDistance) {
                        bestDistance = distance;
                        bestRow = i;
                        bestColumn = j;
                    }
                }
            }
        }
        for (int i = 0; i < numberOfTickets; i++) {
            bestSeats.add(roomMatrix.get(bestRow).get(bestColumn + i));
        }
        return bestSeats;
    }

}
