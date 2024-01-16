import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserloginComponent } from './userlogin/userlogin.component';
import { SearchflightComponent } from './searchflight/searchflight.component';
import { AppComponent } from './app.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { FlightListComponent } from './flight-list/flight-list.component';
import { AuthGuard } from './auth.guard';
import { FlightComponent } from './flight/flight.component';
import { BookingListComponent } from './booking-list/booking-list.component';
import { BookingComponent } from './booking/booking.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';

const routes: Routes = [
  {path: 'welcome', component: WelcomeComponent},
  {path: '', redirectTo: 'welcome', pathMatch: 'full'},
  {path: 'userregister', component: UserRegisterComponent},
  {path: 'userlogin', component: UserloginComponent},
  {path: 'updateuser', component: UpdateUserComponent,canActivate: [AuthGuard]},
  {path: 'searchflight', component: SearchflightComponent,canActivate: [AuthGuard]},
  {path: 'flightlist', component: FlightListComponent,canActivate: [AuthGuard]},
  {path: 'flightdetails/:id', component: FlightComponent,canActivate: [AuthGuard]},
  {path: 'bookinglist', component: BookingListComponent,canActivate: [AuthGuard]},
  {path: 'bookingdetails', component: BookingComponent,canActivate: [AuthGuard]},
  {path: 'createbooking', component: CreateBookingComponent,canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
