package com.fullstack.B_Airways_Backend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int bookingid;

    //eka booking ekata seat 1 , seat 1 ta
    @NotNull
    private int seat;

    @NotNull
    private int price;

    @NotNull
    @Column(columnDefinition = "VARCHAR(60) CHECK (classname IN ('Economy', 'Business', 'Platinum'))")
    private String classname;
}
