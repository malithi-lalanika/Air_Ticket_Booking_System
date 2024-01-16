package com.fullstack.B_Airways_Backend.repo;

import com.fullstack.B_Airways_Backend.entity.Aircraft;
import com.fullstack.B_Airways_Backend.entity.Booking;
import com.fullstack.B_Airways_Backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepo extends JpaRepository<Booking,Integer> {
    Optional<Booking> findBybookingid(int bookingid);
}
