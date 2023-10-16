import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { User } from '../user';
import { Cart } from '../cart';
import { CustomerDetail } from '../customer-detail';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent 
{


  user: User = new User();
  customer:CustomerDetail = new CustomerDetail();
  cartObj:Cart = new Cart();

  verificationCode: any;
  codeInput: any = false;
  buttonForSendingCode: any = true;
  isUserVerified: any = false;

  onInit()
  {}
  constructor(private credeService:CredentialService, private router:Router)
  {}

  register()
  {
    this.user.roles="USER";
    // console.log(this.customer);
    this.customer.emailId =  this.user.emailId;
    this.credeService.addCustomerInCart(this.user.emailId).subscribe();
    this.credeService.addCustomerDetails(this.customer).subscribe();
    // this.credeService.register(this.user).subscribe();
    this.router.navigate(["/login"]);
  }

  sendVerificationCode(){
    this.user.roles = "USER";
    this.credeService.register(this.user).subscribe();
    this.codeInput = true;
    this.buttonForSendingCode = false;
  }
  verifyCode(){
    this.credeService.verifyEmail(this.verificationCode).subscribe(data => {
      if (data) 
    {
        this.isUserVerified = true; 
        
      } else {
        alert("Invalid");
      }});
  }
}
