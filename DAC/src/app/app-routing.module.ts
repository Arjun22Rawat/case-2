import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NewsComponent } from './news/news.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { RegisterComponent } from './register/register.component';
import { CouponsComponent } from './coupons/coupons.component';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { DetailsComponent } from './details/details.component';
import { ProfileComponent } from './profile/profile.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  
  {path:"home",component:HomeComponent},
  {path:"login",component:LoginComponent},
  {path:"profile",component:ProfileComponent},
  {path:"news",component:NewsComponent},
  {path:"about",component:AboutUsComponent},
  {path:"register",component:RegisterComponent},
  {path:"coupons",component:CouponsComponent},
  {path:"cart",component:CartComponent},
  {path:"details",component:DetailsComponent},
  {path:"details/:id",component:DetailsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule 
{ }
