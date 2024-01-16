package com.fullstack.B_Airways_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingResponceDTO {
    private Map<String, List<Integer>> bookedseats;
    private Map<String,Integer> allseatcounts;
}
