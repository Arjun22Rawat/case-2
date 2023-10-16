import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { User } from '../user';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent 
{
  user:User = new User();
  isDataValid:any;
  constructor(private service:CredentialService, private router:Router,private app:AppComponent)
  {

  }

  onInit()
  {
    
  }
  loginForm()
  {
    this.service.loginForm(this.user).subscribe(data=>{this.isDataValid=data;
      if(this.isDataValid)
      {
        this.app.isLogin=true;
        this.service.generateToken(this.user).subscribe((data:any)=>{
          console.log(data);
          this.service.token = data.token;
        });
        this.router.navigate(["/home"]);
      }
      else
      {
        alert("Username or Password is in correct.");
      }      
    });
    
  }
  
}
