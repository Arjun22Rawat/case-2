import { Component } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { Router } from '@angular/router';
import { User } from './user';
import { CredentialService } from './credential.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Deal and Coupons';
  
  user:User = new User();
  userObj:any;
  isAdmin:any = false;
  login:any;
  isLogin:any = false;
  token:any;
  
  constructor(private service:CredentialService,private router:Router)
  {
    
  }
  ngOnInit()
  {

  }
  navigateToCart(){
    this.router.navigate(["/cart"]);
  }
  

  getUserDetails(token: any)

  {
    console.log("hghjqvbqsns");

    this.service.getUserById().subscribe((data:any)=>{

      this.userObj = JSON.parse(data);
      this.user = this.userObj;
      this.user.token = token;
      // this.user = this.service.userObj;
      console.log(this.userObj);
      console.log(this.user);

      if(this.user.roles=="ADMIN")

      {

        this.isAdmin = true;

      }

      else

      {

        this.isAdmin = false;

      }

      console.log(this.isAdmin+"jhjdhkh");

    });

   

  }
  logout()
  {
    this.service.userObj= {};
    this.isAdmin = false;
    this.isLogin=false;
    this.router.navigate(["/home"]);
  }
 
}
