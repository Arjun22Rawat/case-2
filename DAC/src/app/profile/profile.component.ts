import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent 
{

  details:any;
  constructor(private service:CredentialService,private router:Router, private app:AppComponent){}

  ngOnInit()
  {
    this.service.getCustomerDetails().subscribe((data: any)=> {
      this.details=JSON.parse(data);
      console.log(this.details);
    });
  }

  logout()
  {
    this.service.userObj= {};
    this.app.isLogin = false;
    this.router.navigate(["/home"]);
  }
}
