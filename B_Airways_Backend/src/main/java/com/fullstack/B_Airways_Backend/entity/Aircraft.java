package com.fullstack.B_Airways_Backend.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int aircraftid;

    @NotNull
    private int economyseatcount;

    @NotNull
    private int businessseatcount;

    @NotNull
    private int platinumseatcount;

//    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
//    private List<Seat> seats;

    //eka aircraft ekata seat godayi

    @OneToMany(targetEntity = Seat.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="aircraftid",referencedColumnName = "aircraftid")
    private List<Seat> seats;

    @OneToMany(targetEntity = Flight.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="aircraftid",referencedColumnName = "aircraftid")
    private List<Flight> flight;
}
