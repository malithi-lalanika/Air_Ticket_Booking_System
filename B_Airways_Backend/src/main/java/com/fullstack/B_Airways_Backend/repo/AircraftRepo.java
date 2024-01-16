package com.fullstack.B_Airways_Backend.repo;
import com.fullstack.B_Airways_Backend.dto.AircraftDTO;
import com.fullstack.B_Airways_Backend.dto.OrderResponse;
import com.fullstack.B_Airways_Backend.entity.Aircraft;
import com.fullstack.B_Airways_Backend.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AircraftRepo extends JpaRepository<Aircraft,Integer>  {
    //@Query("SELECT a FROM AIRCRAFT a JOIN a.flight f")
    //List<Aircraft> getAircraftByFlightID(String flightid);

    @Query("SELECT new com.fullstack.B_Airways_Backend.dto.OrderResponse(a.aircraftid , f.flightid) FROM Aircraft a JOIN a.flight f")
    public List<OrderResponse> getJoinInformation();

    @Query("SELECT new com.fullstack.B_Airways_Backend.dto.AircraftDTO(a.aircraftid , a.economyseatcount,a.businessseatcount,a.platinumseatcount) FROM Aircraft a JOIN a.flight f WHERE f.flightid=?1")
    public List<AircraftDTO> getAircraftByFlightID(int flightid);
}
