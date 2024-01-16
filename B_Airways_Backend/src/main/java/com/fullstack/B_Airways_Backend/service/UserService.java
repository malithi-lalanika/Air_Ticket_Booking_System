package com.fullstack.B_Airways_Backend.service;

import com.fullstack.B_Airways_Backend.dto.*;
import com.fullstack.B_Airways_Backend.entity.*;
import com.fullstack.B_Airways_Backend.repo.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    public static Map< String, Object > usercache = new HashMap< >();
    @Autowired
    private UserRepo userRepo;                 //mehtods call kirimata

    @Autowired
    private FlightRepo flightRepo;                 //mehtods call kirimata

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private AircraftRepo aircraftRepo;

    @Autowired
    private AirportRepo airportRepo;

    @Autowired
    private ModelMapper modelMapper;


    public static Map < String, Object > getUserCache() {
        return usercache;
    }

    public static void putUserCache(String key, Object value) {
        usercache.put(key, value);
    }

    public static void clearUserCache() {
        usercache.clear();
    }

    public ResponseDTO userRegisterService(User user){
        //userDTO.setName(userDTO.getName()+"set");        //getters and setters
        ResponseDTO responseDTO = new ResponseDTO();        //return kirimata
        List<User> exists=userRepo.findByEmail(user.getEmail());

        if(exists.size()>0){
            responseDTO.setSuccess(false);
            responseDTO.setStatus(0);
            return responseDTO;
        }

        else{
            int strength = 10; // work factor of bcrypt
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepo.save(user);

            UserService.putUserCache("userid", user.getUserid());   //All ids are int in database
            UserService.putUserCache("email", user.getEmail());
            UserService.putUserCache("name", user.getName());
            UserService.putUserCache("contactno", user.getContactno());


            responseDTO.setSuccess(true);
            responseDTO.setStatus(1);

            return responseDTO;
        }
    }

    public ResponseDTO userLoginService(LoginDTO loginDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        List<User> exists=userRepo.findByEmail(loginDTO.getEmail());

        if(exists.size()<1){
            responseDTO.setSuccess(false);
            responseDTO.setStatus(0);
            return responseDTO;
        }

        else{
            User user=userRepo.getUserByEmail(loginDTO.getEmail());

            int strength = 10;
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
            boolean ismatch= bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword());   //input pwd, bycrypted pwd

            if (ismatch){
                UserService.putUserCache("userid", user.getUserid());   //All ids are int in database
                UserService.putUserCache("email", user.getEmail());
                UserService.putUserCache("name", user.getName());
                UserService.putUserCache("contactno", user.getContactno());

                responseDTO.setSuccess(true);
                responseDTO.setStatus(1);
            }
            else{
                responseDTO.setSuccess(false);
                responseDTO.setStatus(0);
            }

            return responseDTO;
        }

    }

    public ResponseDTO userLogoutService(){
        UserService.clearUserCache();

        //System.out.println("Final Mappings are: " + UserService.getUserCache());

        ResponseDTO responseDTO = new ResponseDTO();        //return kirimata
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);

        return responseDTO;
    }

    public ResponseDTO updateUserService(User userDetails){

        Object logUser=UserService.getUserCache().get("userid");
        int logUserID= (int) logUser;
        //System.out.println(String.valueOf(logUserID));

        User user=userRepo.getUserByUserID(String.valueOf(logUserID));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setContactno(userDetails.getContactno());

        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(userDetails.getPassword());
        user.setPassword(encodedPassword);

        User updateduser= userRepo.save(user);

        ResponseDTO responseDTO = new ResponseDTO();        //return kirimata
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);

        return responseDTO;
    }

    public User getUserByUserIDService(){
        Object logUser=UserService.getUserCache().get("userid");
        int logUserID= (int) logUser;

        User user=userRepo.getUserByUserID(String.valueOf(logUserID));
        //user.setPassword("password");
        return user;
    }

    public ResponseDTO bookAflightService(BookingDTO bookingDTO){
        Booking booking=new Booking();
        booking.setSeat(bookingDTO.getSeat());
        booking.setPrice(bookingDTO.getPrice());
        booking.setClassname(bookingDTO.getClassname());
        bookingRepo.save(booking);
        System.out.print(bookingDTO);

        Object logUser=UserService.getUserCache().get("userid");
        int logUserID= (int) logUser;
        User user=userRepo.getUserByUserID(String.valueOf(logUserID));
        List<Booking> userbooking=user.getBooking();
        userbooking.add(booking);
        userRepo.save(user);

        Flight flight=flightRepo.getFlightByFlightID(String.valueOf(bookingDTO.getFlightid()));
        List<Booking> flightbooking=flight.getBooking();
        flightbooking.add(booking);
        flightRepo.save(flight);

        ResponseDTO responseDTO = new ResponseDTO();        //return kirimata
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);

        return responseDTO;

    }

    public List<Flight> getFlightsService(SearchFlightDTO searchFlightDTO){
        List<Flight> flights=flightRepo.getFlights(searchFlightDTO.getOrigin(),searchFlightDTO.getDestination());
        return flights;
    }

    public BookingResponceDTO getBookingsService(String flightID){
        System.out.println(flightID);
        Flight flight=flightRepo.getFlightByFlightID(flightID);

        List<Booking> bookings=flight.getBooking();
        Map<String, List<Integer>> seatsByClass = bookings.stream()
                .collect(Collectors.groupingBy(Booking::getClassname,
                        Collectors.mapping(Booking::getSeat, Collectors.toList())));
        System.out.println(seatsByClass);   //Bookings {Economy=[4, 1], Business=[1]}

        BookingResponceDTO bookingResponceDTO=new BookingResponceDTO();
        bookingResponceDTO.setBookedseats(seatsByClass);

        List<AircraftDTO> aircrafts= aircraftRepo.getAircraftByFlightID(Integer.parseInt(flightID));
        //System.out.println(aircrafts);  //aircraft
        AircraftDTO aircraft=aircrafts.get(0);
        Map<String,Integer> allseats=new HashMap<>();
        allseats.put("Economy",aircraft.getEconomyseatcount());
        allseats.put("Business",aircraft.getBusinessseatcount());
        allseats.put("Platinum",aircraft.getPlatinumseatcount());
        bookingResponceDTO.setAllseatcounts(allseats);

        return bookingResponceDTO;
    }

    public List<UserBookingDTO> getBookingsByuserID(){
        Object logUser=UserService.getUserCache().get("userid");
        int logUserID= (int) logUser;
        User user=userRepo.getUserByUserID(String.valueOf(logUserID));
        List<Booking> userbooking=user.getBooking();
        List<UserBookingDTO> userbookingDTOs=new ArrayList<>();
        for (Booking booking: userbooking){
            int bookingID=booking.getBookingid();
            UserBookingDTO bookinginfo= flightRepo.getFlightByBookingID(bookingID);
            userbookingDTOs.add(bookinginfo);
        }
        return userbookingDTOs;
    }

    public List<Airport> getAllAirports(){
        return airportRepo.findAllByOrderByAirportnameAsc();
    }

    public Optional<Flight> getFlightByFlightID(String FlightID){
        return flightRepo.findByFlightid(Integer.parseInt(FlightID));
    }

    public ResponseDTO DeleteBookingservice(String bookingID){
        Optional<Booking> booking=bookingRepo.findBybookingid(Integer.parseInt(bookingID));
        bookingRepo.delete(booking.get());

        ResponseDTO responseDTO = new ResponseDTO();        //return kirimata
        responseDTO.setSuccess(true);
        responseDTO.setStatus(1);

        return responseDTO;

    }


}


