import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { AvailableSeat, BookAseat } from '../data-types';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent {

  flightDetails : any;
  encrypteData : any;
  bookAseat : BookAseat = new BookAseat();
  seats: number[]=[];
  classes: String[];
  availableSeats: AvailableSeat = new AvailableSeat();

  constructor(private userService: UserService,
    private router: Router,
    private route: ActivatedRoute,
    private popupService: PopupService){}

  ngOnInit(): void {
    this.encrypteData=this.route.snapshot.paramMap.get('details');
    this.flightDetails=JSON.parse(atob(this.encrypteData));
    this.bookAseat.flightid=this.flightDetails.flightid;
    this.classes=["Economy","Business","Platinum"];

    //this.seats=[10,11,13,14];
    this.userService.getSeatNumbers(this.bookAseat.flightid).subscribe( data =>{
      console.log(data);
      this.availableSeats=data;
      console.log(this.availableSeats.bookedseats["Economy"]);
    },
    error => console.log(error));
  }

  createBooking(){
    this.userService.createAbooking(this.bookAseat).subscribe( data =>{
      console.log(data);
        this.popupService.openPopup("Booking Successfull!!!");
        this.router.navigate(['/bookinglist']);
    },
    error => console.log(error));
  }

  onClassChange(className: string){
    this.seats = this.getavaiableSeats(className);
    this.bookAseat.price=(this.flightDetails.price)*(this.classes.indexOf(className)+1);
  }

  getavaiableSeats(className: string): number[]{
    const bookedSeats = this.availableSeats.bookedseats[className] || [];
    const totalSeats = this.availableSeats.allseatcounts[className] || 0;

    const bookedSet = new Set(bookedSeats);

    // Use Array.from to create an array of all seat numbers from 1 to totalSeats
    const allSeats = Array.from({ length: totalSeats }, (_, index) => index + 1);

    // Use array filter to get only the seats that are not booked
    const availableSeats = allSeats.filter(seatNumber => !bookedSet.has(seatNumber));

    return availableSeats;
  }

  onSubmit(){
    if(this.bookAseat.classname==null || this.bookAseat.seat==null){
      this.popupService.openPopup("Class and Seat Number should not be Empty!!!");
    }
    else{
      //console.log(this.login);
      this.createBooking();
    }
  }


}
