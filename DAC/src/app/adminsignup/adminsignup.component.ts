import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { User } from '../user';
@Component({
  selector: 'app-adminsignup',
  templateUrl: './adminsignup.component.html',
  styleUrls: ['./adminsignup.component.css']
})
export class AdminsignupComponent {
  authDetail: User = new User();

 

  verificationCode: any;

  codeInput: any = false;

  buttonForSendingCode: any = true;

  isUserVerified: any = false;

 

  constructor(private detailService: CredentialService){}

 

  verifyCode(){

    this.detailService.verifyEmail(this.verificationCode).subscribe(data => {

      if(data){

        this.isUserVerified = true;

      }

    else{

      alert("Code is invalid");

    }});

  }

  sendVerificationCode(){

    this.authDetail.roles = "ADMIN";

    //this.detailService.sendVerificationCode(this.authDetail).subscribe();

    this.codeInput = true;

    this.buttonForSendingCode = false;

  }
}
