package com.fullstack.B_Airways_Backend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int seatid;

    @NotNull
    @Column(columnDefinition = "VARCHAR(60) CHECK (classname IN ('Economy', 'Business', 'Platinum'))")
    private String classname;

//    @ManyToOne
//    @JoinColumn(name = "aircraftid", referencedColumnName = "aircraftid")
//    private Aircraft aircraft;
}
