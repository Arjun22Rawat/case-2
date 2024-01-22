import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { Cart } from '../cart';
import { ActivatedRoute, Router } from '@angular/router';
declare var Razorpay: any;
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
  itemId:any;
  custId:any;
  amount:any;
  dataObj: any;
  constructor(private route:ActivatedRoute, private service:CredentialService,private router:Router)
  {

  }
  ngOnInit()
  {
    // if(localStorage.getItem("token")==null){
    //    this.router.navigate(["/login"])
    // }
    // this.service.getItemToCart().subscribe(data=>this.items=data);
    // this.custId = this.service.userObj.emailId;
    // this.service.getCustomerDetails().subscribe((data: any)=>{
    //   this.dataObj = JSON.parse(data);
    //   this.amount = this.dataObj.totalAmount;

    // });
    this.service.getItemToCart().subscribe(data=>this.items=data);
    this.custId = this.route.snapshot.paramMap.get('emailId');
    this.custId= this.service.userObj.emailId;
    // console.log(this.items.quantity);

    
  }


  removeItem(itemId:string){
    console.log(itemId);
    this.service.deleteItemFromcart(itemId).subscribe();
    alert("Item removed successfully");
    this.router.navigateByUrl('/cart', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/cart', this.custId]);
    });
  }

  // Increment quantity of an item
  incrementQuantity(item: any) {
    item.quantity++;
    this.updateCartTotal();
  }
 
  // Decrement quantity of an item
  decrementQuantity(item: any) {
    if (item.quantity > 0) {
      item.quantity--;
      this.updateCartTotal();
    }
  }
 
  // Recalculate cart total based on updated quantities
  updateCartTotal() {
    this.cart.cartTotal = this.items.reduce((total, item) => total + (item.price * item.quantity), 0);
    this.visible = true;
  }

  calculateTotal()
  {
    this.service.cartTotal().subscribe(data=>this.cart.cartTotal=data);
    // this.cart.cartTotal = this.total;
    this.visible= true;
  }

  BuyItemsFromCart(){
    let productAmount=this.cart.cartTotal;
   console.log(this.cart.cartTotal);
    this.service.createTransaction(productAmount).subscribe(
   
      (response) => {
        console.log(response);
          this.openTransactionModel(response);
      },
      (error) => {
        console.log(error);
      });
  }
 
  openTransactionModel(response: any){
    var options = {
      order_id: response.order_id,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      
      name: 'Arjun',
      description: 'Payment',
      image: 'C:\Users\MicroSoft\Pictures\Screenshots.png',
      handler: (response: any) => {
        if (response.razorpay_payment_id) {
          // Payment successful, now perform the required actions
          this.processResponse(response);
          this.service.deleteAllItemsFromCart().subscribe(() => {
            // Navigating to the cart page or other actions after successful payment and cart deletion
            this.router.navigateByUrl('/cart', { skipLocationChange: true }).then(() => {
              this.router.navigate(['/cart', this.custId]);
              this.router.navigate(["/home"]);
            });
            alert("You Purchase the Item Successfully");
          });
        } else {
          console.log('Payment was not successful.');
          alert("Payment is cancelled")
        }
      },
      prefill: {
        name: 'Arjun',
        email: 'arjunrawat2002@gmail',
        contact: '722046127'
      },
      notes:{
        address: 'GHHASKN'
      },
      theme: {
        color: '#F37254'
      }
    };
 
    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
 
  }
 
  processResponse(resp: any){
    console.log(resp);
    this.amount -= this.cart.cartTotal;
    this.service.updatePrice(this.amount).subscribe();
    if(this.service.isCouponCodeApplied){
      this.service.deleteCoupon(this.service.couponCodeTORedeem).subscribe();
      this.service.isCouponCodeApplied = false;
      this.service.couponCodeTORedeem = "";
    }
    //console.log(this.amount);
  }



}

