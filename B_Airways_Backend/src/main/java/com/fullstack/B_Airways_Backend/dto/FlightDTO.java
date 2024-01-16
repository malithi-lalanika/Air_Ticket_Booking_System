package com.fullstack.B_Airways_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightDTO {
    private int flightid;
    private String date;
    private String starttime;
    private String endtime;
    private String origin;
    private String destination;
    private int price;
    private int aircraftid;
}
