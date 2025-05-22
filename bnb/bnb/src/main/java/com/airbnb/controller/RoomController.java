package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Room;
import com.airbnb.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/addroom")
    public ResponseEntity<Room> addRoom(@RequestBody Room room, @RequestParam long propertyId, @AuthenticationPrincipal AppUser appUser){
        return new ResponseEntity<>(roomService.addRoom(room,propertyId), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteroom")
    public ResponseEntity<String> deleteRoom(@RequestParam long roomId, @RequestParam long propertyId, @AuthenticationPrincipal AppUser appUser){
        return new ResponseEntity<>(roomService.deleteRoom(roomId,propertyId), HttpStatus.OK);
    }

    @GetMapping("/viewroom")
    public ResponseEntity<?> viewRoom(@RequestParam String roomType, @RequestParam long propertyId) {
        Room room = roomService.viewRoom(roomType, propertyId);
        return room != null ? new ResponseEntity<>(roomService.viewRoom(roomType,propertyId),HttpStatus.OK) : new ResponseEntity<>("Room not found",HttpStatus.OK);
    }

    @PutMapping("/updateroom")
    public ResponseEntity<Room> updateRoom(@RequestParam long propertyId, @RequestParam long roomId, @RequestBody Room updatedRoom, @AuthenticationPrincipal AppUser appUser) {
        return new ResponseEntity<>(roomService.updateRoom(propertyId, roomId, updatedRoom), HttpStatus.OK);
    }
}
