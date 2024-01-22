import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { Cart } from '../cart';


import { User } from '../user';

import { CustomerDetail } from '../customer-detail';

import { Validators, FormControl } from '@angular/forms';
@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent {
  items:Items[] = [];
  cart:Cart = new Cart();
  // total:string="";
  
  address:any;
  visible:boolean = false;
  user: User = new User();
  customer:CustomerDetail = new CustomerDetail();
  cartObj:Cart = new Cart();

  Name:any;
  totalamount:any;
  Address:any;
  contactNumber:any;



  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', Validators.required);
  name=new FormControl('',Validators.required);
contact=new FormControl('',Validators.required);
totalAmount=new FormControl('',Validators.nullValidator);

  constructor(private service:CredentialService,private router:Router)
  {

  }
  ngOnInit()
  {
    this.service.getItemToCart().subscribe(data=>this.items=data);
  }
  register()
  {
    this.user.roles="USER";
  
   console.log(this.service.userObj.emailId);
   
    this.service.updateCustomerDetails(this.service.userObj.emailId,this.Name,this.Address,this.contactNumber,this.totalamount).subscribe();
    console.log(this.Address);
    console.log(this.contactNumber);
    console.log(this.totalamount);
    // this.credeService.register(this.user).subscribe();
    this.router.navigate(["/home"]);
  }
 

}
