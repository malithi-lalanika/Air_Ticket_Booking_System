import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { Airport, UserBooking } from '../data-types';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent {

  bookingdetails : UserBooking=new UserBooking();
  encrypteData : any;

  constructor(private userService: UserService,
              private router: Router,
              private route: ActivatedRoute,
              private popupService: PopupService){}

ngOnInit(): void {
  this.encrypteData=this.route.snapshot.paramMap.get('details');
  this.bookingdetails=JSON.parse(atob(this.encrypteData));
  console.log(this.bookingdetails.destination);
}

deleteBooking(id: number){
  this.userService.deleteBooking(id).subscribe( data => {
    console.log(data);
    this.popupService.openPopup("Successfully Deleted!!!");
    this.gotoBookinglist();
  })
}

gotoBookinglist(){
  this.router.navigate(['/bookinglist']);
}

}
