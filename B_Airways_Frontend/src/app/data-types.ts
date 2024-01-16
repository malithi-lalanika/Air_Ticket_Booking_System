export class Login {
    email: String;
    password: String;
  }
  export class Response{
    success: boolean;
    status: number;
  }

export class Search{
  origin: String;
  destination: String;
}

export class BookAseat{
  price: number;
  seat: number;
  flightid: number;
  classname: string;
}

export class AvailableSeat{
  bookedseats: Record<string, number[]>
  allseatcounts: Record<string, number>
}

export class UserBooking{
  bookingid: number;
  seat: number;
  price: number;
  classname: string;
  flightid: number;
  date: string;
  starttime: string;
  endtime: string;
  origin: string;
  destination: string;
}



export class Booking {
    bookingid: number;
    seat: number;
    price: number;
    classname: string;
}

export class User {
    userid: number;
    name: string;
    email: string;
    password: string;
    contactno: string;
    booking: Booking[];
}

export class Flight{
  flightid: number;
  date: String;
  starttime: String;
  endtime: String;
  origin: String;
  destination: String;
  price: number;
  booking: Booking[];
}

export class Airport{
  airportid: number;
  airportname: String;
}

