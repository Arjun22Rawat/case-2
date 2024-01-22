import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { User } from '../user';
import { Cart } from '../cart';
import { CustomerDetail } from '../customer-detail';
import { Router } from '@angular/router';
import { Validators, FormControl } from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent 
{

  form: FormGroup;
  user: User = new User();
  customer:CustomerDetail = new CustomerDetail();
  cartObj:Cart = new Cart();

  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', Validators.required);
  name=new FormControl('',Validators.required);
contact=new FormControl('',Validators.required);
totalAmount=new FormControl('',[Validators.nullValidator,Validators.min(0)]);

  verificationCode: any;
  codeInput: any = false;
  buttonForSendingCode: any = true;
  isUserVerified: any = false;

  onInit()
  {}
  constructor(private credeService:CredentialService, private router:Router,private fb: FormBuilder)
  {
    this.form = this.fb.group({
      totalAmount: [null, [Validators.required, Validators.min(0)]],
    });
  }

  register()
  {
    this.user.roles="USER";
    // console.log(this.customer);
    this.customer.emailId =  this.user.emailId;
    this.credeService.addCustomerInCart(this.user.emailId).subscribe();
    this.credeService.addCustomerDetails(this.customer).subscribe();
    console.log(this.customer);
    // this.credeService.register(this.user).subscribe();
    this.router.navigate(["/login"]);
  }

  checkUser() {

    // Check if the user already exists

    this.credeService.checkUsersExists(this.user.emailId).subscribe((userExists: boolean) => {

      if (userExists) {

        // User already exists, handle accordingly (e.g., show an error message)

        alert('User already exists!');

      } else {

        // User does not exist, proceed with registration

        this.sendVerificationCode();

      }

    });

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
