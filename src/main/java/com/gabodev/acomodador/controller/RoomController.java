package com.gabodev.acomodador.controller;

import com.gabodev.acomodador.dto.ChairDto;
import com.gabodev.acomodador.dto.PurchaseTicketRequest;
import com.gabodev.acomodador.service.IRoomService;
import com.gabodev.acomodador.util.LogUtils;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, List<ChairDto>> getRoom() {
        LogUtils.logInfo("Room request received");
        List<ChairDto> resRoom = roomService.getRoom();
        return Map.of("room", resRoom);
    }

    @PostMapping("/tickets/purchase")
    public Map<String, List<ChairDto>> purchaseTickets(@RequestBody PurchaseTicketRequest body) {
        LogUtils.logInfo("Purchase request received: " + body);
        int numberOfTickets = body.getAmmount();
        List<ChairDto> resChairs = roomService.assignBestSeats(numberOfTickets);
        return Map.of("chairs_reserved", resChairs);
    }
}
