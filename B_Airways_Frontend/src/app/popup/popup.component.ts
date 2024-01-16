import { Component,Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { PopupService } from './popup.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent {

  message: String;

  constructor(
    private popupsevice: PopupService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ){
    this.message=this.data;
  }

  closeDialog(){
    this.popupsevice.closePopup();
  }

}
