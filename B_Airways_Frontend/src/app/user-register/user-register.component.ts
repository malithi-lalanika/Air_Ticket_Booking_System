import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../data-types';
import { UserService } from '../user.service';
import { PopupService } from '../popup/popup.service';
import { Response } from '../data-types';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {

  user: User = new User();
  response: Response = new Response();

  constructor(private userService: UserService,
              private router: Router,
              private popupService: PopupService){}

  ngOnInit(): void {
    
  }

  userRegister(){
    this.userService.userRegisterService(this.user).subscribe( data =>{
      console.log(data);
      this.response=data;
      if(this.response.success){
        localStorage.setItem('isLoggedIn', '1');
        this.popupService.openPopup("Register Successfull!!!");
        this.router.navigate(['/searchflight']);
      }
      else{
        this.popupService.openPopup("Email is already Registered. Please Login!!!");
        this.router.navigate(['/welcome']);
      }
      
    },
    error => console.log(error));
  }

  onSubmit(){
    //console.log(this.user);
    if(this.user.email==null || this.user.password==null || this.user.name==null || this.user.contactno==null){
      this.popupService.openPopup("Please fill All fields!!!");
    }

    else{
      this.userRegister();
    }
    
    
  }

}
