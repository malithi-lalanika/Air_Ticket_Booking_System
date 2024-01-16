import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { Flight } from '../data-types';

@Component({
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent {

  id: number
  flight: Flight
  flightDetails: any;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private router: Router,) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.flight = new Flight();
    this.userService.getFlightById(this.id).subscribe( data => {
      this.flight = data;
    });
  }

  bookAflight(){
    this.flightDetails={
      "flightid":this.flight.flightid,
      "price": this.flight.price
    }
    this.router.navigate(['createbooking',{details:btoa(JSON.stringify(this.flightDetails))}]);
  }

}
