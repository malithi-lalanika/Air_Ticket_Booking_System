import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { PopupService } from '../popup/popup.service';
import { Flight, Search } from '../data-types';
import { Airport } from '../data-types';

@Component({
  selector: 'app-searchflight',
  templateUrl: './searchflight.component.html',
  styleUrls: ['./searchflight.component.css']
})
export class SearchflightComponent {

search: Search = new Search();
airports: Airport[];

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private popupService: PopupService) { }

  ngOnInit(): void {
    this.userService.getAirportsList().subscribe(data => {
      this.airports = data;
      //console.log(this.airports[0].airportname)
    }, error => console.log(error));
  }


  goToFlightsList(){
    this.router.navigate(['/flightlist',{search:JSON.stringify(this.search)}]);
  }

  onSubmit(){
    if(this.search.origin==null || this.search.destination==null){
      this.popupService.openPopup("Please Select Both Origin And Destination!!!");
    }
    else{
      this.goToFlightsList();
    }
    
  }

}
