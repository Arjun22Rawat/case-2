import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { Cart } from '../cart';
@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent {
  items:Items[] = [];
  cart:Cart = new Cart();
  // total:string="";
  visible:boolean = false;
  constructor(private service:CredentialService)
  {

  }
  ngOnInit()
  {
    this.service.getItemToCart().subscribe(data=>this.items=data);
  }
  calculateTotal()
  {
    this.service.cartTotal().subscribe(data=>this.cart.cartTotal=data);
    // this.cart.cartTotal = this.total;
    this.visible= true;
  }

}
