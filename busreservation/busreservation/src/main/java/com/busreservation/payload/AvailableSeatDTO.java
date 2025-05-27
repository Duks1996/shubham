package com.busreservation.payload;

import com.busreservation.entity.Bus;
import lombok.Getter;

@Getter
public class AvailableSeatDTO {
    private Long seatId;
    private String seatName;
    private Bus bus;

    public AvailableSeatDTO(Long seatId, String seatName, Bus bus) {
        this.seatId = seatId;
        this.seatName = seatName;
        this.bus=bus;
    }
}
