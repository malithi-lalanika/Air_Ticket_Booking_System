import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserloginComponent } from './userlogin/userlogin.component';
import { SearchflightComponent } from './searchflight/searchflight.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PopupComponent } from './popup/popup.component';
import { MatDialogModule } from '@angular/material/dialog';
import { UpdateUserComponent } from './update-user/update-user.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import { FlightComponent } from './flight/flight.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { BookingComponent } from './booking/booking.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';

@NgModule({
  declarations: [
    AppComponent,
    UserloginComponent,
    SearchflightComponent,
    UserRegisterComponent,
    WelcomeComponent,
    PopupComponent,
    UpdateUserComponent,
    FlightListComponent,
    FlightComponent,
    BookingListComponent,
    BookingComponent,
    CreateBookingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
