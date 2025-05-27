package com.busreservation.service;

import com.busreservation.entity.*;
import com.busreservation.payload.AvailableSeatDTO;
import com.busreservation.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {
    private BusRepository busRepository;
    private StopRepository stopRepository;
    private StopOrderRepository stopOrderRepository;
    private SeatRepository seatRepository;
    private SeatReservationRepository seatReservationRepository;

    public BusService(BusRepository busRepository, StopRepository stopRepository, StopOrderRepository stopOrderRepository, SeatRepository seatRepository, SeatReservationRepository seatReservationRepository) {
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.stopOrderRepository = stopOrderRepository;
        this.seatRepository = seatRepository;
        this.seatReservationRepository = seatReservationRepository;
    }

    public List<AvailableSeatDTO> getAvailableSeats(Long busId, String fromStopName, String toStopName, LocalDate date) {
        // validating bus, stops
        Bus bus = busRepository.findById(busId).orElseThrow(() -> new RuntimeException("Bus not found"));
        Stop fromStop = stopRepository.findByName(fromStopName).orElseThrow(() -> new RuntimeException("From stop not found"));
        Stop toStop = stopRepository.findByName(toStopName).orElseThrow(() -> new RuntimeException("To stop not found"));

        // getting stop_order ids from names
        int fromIndex = stopOrderRepository.findByBusAndStop(bus, fromStop).orElseThrow(() -> new RuntimeException("From stop not in order")).getStoporder().intValue();
        int toIndex = stopOrderRepository.findByBusAndStop(bus, toStop).orElseThrow(() -> new RuntimeException("to stop not in order")).getStoporder().intValue();

        //from index should be first then to index
        if (fromIndex >= toIndex) {
            throw new RuntimeException("Invalid stop sequence");
        }

        //Get all reserved seats overlapping the segment
        List<Long> occupiedSeatIds = seatReservationRepository.findOccupiedSeatIds(busId, date, fromIndex, toIndex);

        //Get all seats and exclude the occupied ones
        return seatRepository.findAllByBusId(busId).stream()
                .filter(seat -> !occupiedSeatIds.contains(seat.getId()))
                .map(seat -> new AvailableSeatDTO(seat.getId(), seat.getName(),seat.getBus()))
                .collect(Collectors.toList());
    }
}
