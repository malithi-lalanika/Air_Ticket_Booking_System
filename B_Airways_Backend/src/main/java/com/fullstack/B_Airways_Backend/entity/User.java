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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userid;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String contactno;

    @OneToMany(targetEntity = Booking.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="userid",referencedColumnName = "userid")
    private List<Booking> booking;
}
