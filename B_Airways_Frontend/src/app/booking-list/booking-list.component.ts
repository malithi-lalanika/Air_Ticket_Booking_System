import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { Airport, UserBooking } from '../data-types';


@Component({
  selector: 'app-booking-list',
  templateUrl: './booking-list.component.html',
  styleUrls: ['./booking-list.component.css']
})
export class BookingListComponent {

  bill = {
    'customerName': "RAJESH",
    'billNo': "A230",
    'date': "23/12/2020",
    'address': "AGRA",
    'itemDetails':"HUSQVANA VITIPILEN",
    'grandTotal': 2500000
  }
  airport: Airport= new Airport();
  bookinglist: UserBooking[];

  constructor(private userService: UserService,
    private router: Router,){}

ngOnInit(): void {
  this.getBookings();
}

userClick(airportinput: Airport){    
  this.airport={"airportid":1,"airportname":"abc"};                       
  this.router.navigate(['/bookingdetails',{airport:btoa(JSON.stringify(this.airport))}]);
}

private getBookings(){
  this.userService.getUserBookingList().subscribe( data =>{
    //this.popupService.openPopup("Update Successfull!!!");
    console.log(data);
    this.bookinglist=data;

    const sortByDate = (a: any, b: any) => {
      const dateA = new Date(a.date.split('/').reverse().join('/'));
      const dateB = new Date(b.date.split('/').reverse().join('/'));
  
      return dateA.getTime() - dateB.getTime();
  };
  this.bookinglist.sort(sortByDate);
  }
  , error => console.log(error));
}

bookingDetails(userbooking: UserBooking){
  this.router.navigate(['/bookingdetails',{details:btoa(JSON.stringify(userbooking))}]);
}

}
