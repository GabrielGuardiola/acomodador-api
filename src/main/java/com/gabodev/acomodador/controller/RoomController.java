package com.gabodev.acomodador.controller;

import com.gabodev.acomodador.dto.RowDto;
import com.gabodev.acomodador.service.IRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
        roomService.init();
    }

    @GetMapping("/room")
    public Map<String, List<RowDto>> getRoom() {
        List<RowDto> resRoom = roomService.getRoom();
        return Map.of("room", resRoom);
    }
}
