import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './user.service';
import * as myGlobals from './globals'; //<==== this one (**Updated**)
import { PopupService } from './popup/popup.service';

//helloString: string="hello " + myGlobals.sep

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'B_Airways_Frontend';
  //isLoggedin= false;
  isLoggedinstatus="0";
  
  constructor(private router: Router,
              private userService: UserService,
              private popupService: PopupService){
  }

  ngOnInit() {
    //localStorage.setItem('isLoggedIn', '0');
     this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.isLoggedinstatus = localStorage.getItem('isLoggedIn') || "";  //nav bar eke event eka logging status eka anuwa wenas kirima
      }
    })
    
    
  }

  userLogout(){
    this.userService.userLogoutService().subscribe( data =>{
      console.log(data);
      localStorage.setItem('isLoggedIn', '0');
      this.popupService.openPopup("Logout Successfull!!!");
      this.router.navigate(['/welcome']);
    },
    error => console.log(error));
  }
  
}
