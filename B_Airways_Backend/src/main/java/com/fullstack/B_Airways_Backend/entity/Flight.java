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
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int flightid;

    //@DateTimeFormat(pattern="dd/MM/yyyy")
    @NotNull
    private String date;

    @NotNull
    private String starttime;

    @NotNull
    private String endtime;

    @NotNull
    private String origin;

    @NotNull
    private String destination;

    @NotNull
    private int price;



    @OneToMany(targetEntity = Booking.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="flightid",referencedColumnName = "flightid")
    private List<Booking> booking;
}
