import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupComponent } from './popup.component';

@Injectable({
  providedIn: 'root',
})
export class PopupService {
  constructor(private dialog: MatDialog) {}

  openPopup(message:String) {
    this.dialog.open(PopupComponent,{
      data: message,
    });
  }

  closePopup(){
    this.dialog.closeAll();
  }
}