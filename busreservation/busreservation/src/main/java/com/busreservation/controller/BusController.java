package com.busreservation.controller;

import com.busreservation.payload.AvailableSeatDTO;
import com.busreservation.service.BusService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) { this.busService = busService; }

    @GetMapping("/available-seats")
    public ResponseEntity<?> getAvailableSeats(
            @RequestParam Long busId,
            @RequestParam String fromStopName,
            @RequestParam String toStopName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        if (date.isBefore(LocalDate.now())) {
            return new ResponseEntity<>("Date must be today or in the future.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(busService.getAvailableSeats(busId, fromStopName, toStopName, date), HttpStatus.OK);
    }
}
