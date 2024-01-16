import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { Login} from '../data-types';
import { Response } from '../data-types';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'app-userlogin',
  templateUrl: './userlogin.component.html',
  styleUrls: ['./userlogin.component.css']
})
export class UserloginComponent {

  login: Login = new Login();
  response: Response = new Response();

  constructor(private userService: UserService,
              private router: Router,
              private popupService: PopupService){}

  ngOnInit(): void {
    
  }

  userLogin(){
    this.userService.userLoginService(this.login).subscribe( data =>{
      console.log(data);
      this.response=data;
      console.log(this.response.status);
      if (this.response.success){
        localStorage.setItem('isLoggedIn', '1');
        this.popupService.openPopup("Logging Successfull!!!");
        this.router.navigate(['/searchflight']);
      }
      else{
        this.popupService.openPopup("Invalid Email OR Password!!!");
        this.router.navigate(['/welcome']);
      }
    },
    error => console.log(error));
  }

  onSubmit(){
    //console.log(this.login);
    if(this.login.email==null || this.login.password==null){
      this.popupService.openPopup("Email and Password should not be Empty!!!");
    }
    else{
      //console.log(this.login);
      this.userLogin();
    }
  }

}
