package com.fullstack.B_Airways_Backend.repo;

import com.fullstack.B_Airways_Backend.entity.Airport;
import com.fullstack.B_Airways_Backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepo extends JpaRepository<Airport,Integer> {
    List<Airport> findAllByOrderByAirportnameAsc();  //this is also like an query
}
