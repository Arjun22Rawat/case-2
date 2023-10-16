import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {MatButtonModule} from '@angular/material/button';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { NewsComponent } from './news/news.component';
import { CouponsComponent } from './coupons/coupons.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { DetailsComponent } from './details/details.component';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AboutUsComponent,
    NewsComponent,
    CouponsComponent,
    HomeComponent,
    CartComponent,
    DetailsComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
