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
  login:any;
  isLogin:any = false;
  constructor(private service:CredentialService,private router:Router)
  {
    
  }
  ngOnInit()
  {

  }
 
}
