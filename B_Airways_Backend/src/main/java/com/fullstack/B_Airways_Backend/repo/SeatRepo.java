package com.fullstack.B_Airways_Backend.repo;

import com.fullstack.B_Airways_Backend.entity.Aircraft;
import com.fullstack.B_Airways_Backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat,Integer> {
}
