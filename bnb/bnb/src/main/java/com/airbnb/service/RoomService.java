package com.airbnb.service;

import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;

    public RoomService(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    public Room addRoom(Room room, long propertyId) {
        Property existing = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));
        room.setProperty(existing);
        return roomRepository.save(room);
    }

    public String deleteRoom(long roomId, long propertyId) {
        propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));
        int deleted = roomRepository.deleteByIdAndPropertyId(roomId, propertyId);
        return deleted > 0 ? "Room deleted" : "Room not found";
    }

    public Room updateRoom(long propertyId,long roomId, Room updatedRoom) {
        propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property not found"));
        Room existingRoom = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        existingRoom.setType(updatedRoom.getType());
        existingRoom.setCount(updatedRoom.getCount());
        existingRoom.setPrice(updatedRoom.getPrice());

        return roomRepository.save(existingRoom);
    }
}
