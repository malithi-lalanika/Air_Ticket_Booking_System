import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

import { UserService } from '../user.service';
import { User } from '../data-types';
import { PopupService } from '../popup/popup.service';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent {

  user: User= new User();

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private popupService: PopupService) { }

    ngOnInit(): void {
      
      this.userService.getUserById().subscribe(data => {
        this.user = data;
        console.log(this.user);
      }, error => console.log(error));
      
    }


    goToSearchFlights(){
      this.userService.updateUser(this.user).subscribe( data =>{
        this.popupService.openPopup("Update Successfull!!!");
        this.router.navigate(['/searchflight']);
      }
      , error => console.log(error));
    }
  
    onSubmit(){
      if(this.user.email==null || this.user.password=="" || this.user.name==null || this.user.contactno==null){
        this.popupService.openPopup("Please fill All fields!!!");
      }
  
      else{
        this.goToSearchFlights();
      }
    }

}
