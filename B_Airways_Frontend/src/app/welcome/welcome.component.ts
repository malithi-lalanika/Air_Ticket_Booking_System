import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LocationStrategy } from '@angular/common';

import { UserService } from '../user.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent {
  constructor(private userService: UserService,
              private router: Router,
              private location: LocationStrategy){
    history.pushState(null, "", window.location.href);  
    this.location.onPopState(() => {
      history.pushState(null, "", window.location.href);
                });
              }

  ngOnInit(): void {
    
  }
  userLogin(){
    this.router.navigate(['/userlogin']);
  }

  userRegister(){
    this.router.navigate(['/userregister']);
  }

}
