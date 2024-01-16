import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Airport, Flight, Login, UserBooking} from './data-types';
import { User } from './data-types';
import { Response } from './data-types';
import { Search } from './data-types';
import { BookAseat,AvailableSeat } from './data-types';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/v1/user";
  //isLoggedIn = false;

  constructor(private httpClient: HttpClient) { }   /**Observable<Response>, return type is Response class */

  userLoginService(login: Login): Observable<Response>{     /** login: Login  , variable:variable type */
    return this.httpClient.post<Response>(`${this.baseURL}/userLogin`,login);
  }

  userRegisterService(user: User): Observable<Response>{     
    return this.httpClient.post<Response>(`${this.baseURL}/userRegister`,user);
  }

  userLogoutService(): Observable<Object>{     
    return this.httpClient.post(`${this.baseURL}/userLogout`,null);
  }

  getUserById(): Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/getUserByUserId/`);
  }

  updateUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/updateUser/`, user);
  }

  searchAflight(search: Search): Observable<Flight[]>{
    return this.httpClient.post<Flight[]>(`${this.baseURL}/getflights`,search);
  }

  getAirportsList(): Observable<Airport[]>{
    return this.httpClient.get<Airport[]>(`${this.baseURL}/getAirports`);
  }

  getFlightById(id: number): Observable<Flight>{
    return this.httpClient.get<Flight>(`${this.baseURL}/getFlightByFlightID/${id}`);
  }

  createAbooking(bookAseat : BookAseat): Observable<Response>{     
    return this.httpClient.post<Response>(`${this.baseURL}/bookAflight`,bookAseat);
  }

  getSeatNumbers(id: number): Observable<AvailableSeat>{
    return this.httpClient.get<AvailableSeat>(`${this.baseURL}/getbookings/${id}`);
  }

  getUserBookingList(): Observable<UserBooking[]>{
    return this.httpClient.get<UserBooking[]>(`${this.baseURL}/getuserbookings`);
  }

  deleteBooking(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteBooking/${id}`);
  }
}
