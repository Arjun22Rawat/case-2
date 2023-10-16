import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { Cart } from '../cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent 
{
  items:Items[] = [];
  cart:Cart = new Cart();
  // total:string="";
  visible:boolean = false;
  constructor(private service:CredentialService)
  {

  }
  ngOnInit()
  {
    this.service.getAddItemToCart().subscribe(data=>this.items=data);
  }
  // getAddItemToCart()
  // {
    
  // }
  calculateTotal()
  {
    this.service.cartTotal().subscribe(data=>this.cart.cartTotal=data);
    // this.cart.cartTotal = this.total;
    this.visible= true;
  }
}
