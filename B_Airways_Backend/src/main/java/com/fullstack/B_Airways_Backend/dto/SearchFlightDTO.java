package com.fullstack.B_Airways_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchFlightDTO {
    private String origin;
    private String destination;
}
