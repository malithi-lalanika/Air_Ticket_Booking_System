import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

import { UserService } from '../user.service';
import { Flight } from '../data-types';
import { Search } from '../data-types';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent {

  flights: Flight[];
  search: Search = new Search();
  routedata: any;
  

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,) { }

    ngOnInit(): void {
      this.routedata=this.route.snapshot.paramMap.get('search');
      this.search=JSON.parse(this.routedata);

      //console.log(this.search);
      this.getFlights();
      
    }

    private getFlights(){
      this.userService.searchAflight(this.search).subscribe( data =>{
        //this.popupService.openPopup("Update Successfull!!!");
        console.log(data);
        this.flights=data;

        const sortByDate = (a: any, b: any) => {
          const dateA = new Date(a.date.split('/').reverse().join('/'));
          const dateB = new Date(b.date.split('/').reverse().join('/'));
      
          return dateA.getTime() - dateB.getTime();
      };
      this.flights.sort(sortByDate);
      }
      , error => console.log(error));
    }

    flightDetails(id: number){
      this.router.navigate(['flightdetails', id]);
    }
  

}
