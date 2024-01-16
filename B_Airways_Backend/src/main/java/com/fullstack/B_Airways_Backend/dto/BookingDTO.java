package com.fullstack.B_Airways_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDTO {
    private int price;
    private int seat;
    private int flightid;
    private String classname;
}
