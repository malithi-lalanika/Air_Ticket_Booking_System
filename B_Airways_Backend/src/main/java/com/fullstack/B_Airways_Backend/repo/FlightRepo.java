package com.fullstack.B_Airways_Backend.repo;

import com.fullstack.B_Airways_Backend.dto.AircraftDTO;
import com.fullstack.B_Airways_Backend.dto.FlightDTO;
import com.fullstack.B_Airways_Backend.dto.UserBookingDTO;
import com.fullstack.B_Airways_Backend.entity.Aircraft;
import com.fullstack.B_Airways_Backend.entity.Flight;
import com.fullstack.B_Airways_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlightRepo extends JpaRepository<Flight,Integer> {
    @Query(value = "SELECT * FROM FLIGHT WHERE FLIGHTID = ?1",nativeQuery = true)  // ?1: palaweni value eka
    Flight getFlightByFlightID(String flightID);

    @Query(value = "SELECT * FROM FLIGHT WHERE ORIGIN =?1 AND DESTINATION =?2",nativeQuery = true)
    List<Flight> getFlights(String origin, String destination);

    @Query("SELECT new com.fullstack.B_Airways_Backend.dto.UserBookingDTO(b.bookingid,b.seat,b.price,b.classname,f.flightid , f.date,f.starttime,f.endtime,f.origin,f.destination) FROM Flight f JOIN f.booking b WHERE b.bookingid=?1")
    public UserBookingDTO getFlightByBookingID(int booking);

    Optional<Flight> findByFlightid(int flightid);
}
