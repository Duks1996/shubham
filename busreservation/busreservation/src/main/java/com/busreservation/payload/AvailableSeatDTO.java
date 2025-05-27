package com.busreservation.payload;

import lombok.Getter;

@Getter
public class AvailableSeatDTO {
    private Long seatId;
    private String seatName;

    public AvailableSeatDTO(Long seatId, String seatName) {
        this.seatId = seatId;
        this.seatName = seatName;
    }
}
