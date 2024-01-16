package com.fullstack.B_Airways_Backend.controller;

import com.fullstack.B_Airways_Backend.dto.*;
import com.fullstack.B_Airways_Backend.entity.Airport;
import com.fullstack.B_Airways_Backend.entity.Flight;
import com.fullstack.B_Airways_Backend.entity.User;
import com.fullstack.B_Airways_Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/userRegister")
    public ResponseDTO userRegisterController(@RequestBody User user) {   //userDTO kiyanne nikan e user wargaya wage
        return userService.userRegisterService(user);

    }

    @PostMapping("/userLogin")
    public ResponseDTO userLoginController(@RequestBody LoginDTO loginDTO) {   //RequestBody ekata aniwa eka object ekayi thiyenne
        return userService.userLoginService(loginDTO);

    }

    @PostMapping("/userLogout")
    public ResponseDTO userLogoutController() {
        //System.out.println("Initial Mappings are: " + UserService.getUserCache());
        return userService.userLogoutService();

    }

    @PostMapping("/updateUser")  //need to login first
    public ResponseDTO updateUser(@RequestBody User userDetails) {

        return userService.updateUserService(userDetails);
    }

    @GetMapping("/getUserByUserId")   //need to login first
    public User getUserByUserID(){

        User user= userService.getUserByUserIDService();
        user.setPassword("");
        return user;
    }

    @PostMapping("/bookAflight")   //need to login first
    public ResponseDTO bookAflight(@RequestBody BookingDTO bookingDTO) {
        return userService.bookAflightService(bookingDTO);
    }
    @PostMapping("/getflights")
    public List<Flight> getFlights(@RequestBody SearchFlightDTO searchFlightDTO) {
        return userService.getFlightsService(searchFlightDTO);
    }

    @GetMapping("/getbookings/{flightid}")
    public BookingResponceDTO getBookings(@PathVariable String flightid) {
        return userService.getBookingsService(flightid);
    }

    @GetMapping("/getuserbookings")   //need to login first
    public List<UserBookingDTO> getBookings() {
        return userService.getBookingsByuserID();
    }

    @GetMapping("/getAirports")
    public List<Airport> getAirport() {
        return userService.getAllAirports();
    }

    @GetMapping("/getFlightByFlightID/{flightID}")
    public Optional<Flight> getFlightByFlightID(@PathVariable String flightID){
        return userService.getFlightByFlightID(flightID);
    }

    @DeleteMapping("/deleteBooking/{bookingID}")
    public ResponseDTO deleteUser(@PathVariable String bookingID) {
        //System.out.print("hiii");
        return userService.DeleteBookingservice(bookingID);
    }

}
