package com.shellinfo.demo.model.dto;

import com.shellinfo.demo.model.Fare;
import lombok.Data;

@Data
public class FareDetailsDto {

    private String fromStationId;
    private String toStationId;
    private String ticketType;
    private int numberOfTickets;
    private double fareBeforeDiscount;
    private double fareAfterDiscount;
    private double finalFare;

    public static FareDetailsDto from(Fare fare) {
        FareDetailsDto dto = new FareDetailsDto();

        dto.setFromStationId(fare.getFromStationId());
        dto.setToStationId(fare.getToStationId());
        dto.setNumberOfTickets(1);
        dto.setTicketType("SJT");
        dto.setFareBeforeDiscount(fare.getFare());
        dto.setFareAfterDiscount(fare.getFare());
        dto.setFinalFare(fare.getFare());

        return dto;
    }
}


