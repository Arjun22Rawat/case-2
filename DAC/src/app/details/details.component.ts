import { Router, ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';

declare var Razorpay: any;
@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent 
{
  itemId:any;
  item:any;
  amount:any;
  productAmount:any;
  dataObj: any;

  constructor(private router:Router, private route:ActivatedRoute, private service:CredentialService)
  {

  }
  ngOnInit()
  {
    this.itemId = this.route.snapshot.paramMap.get('id');
    this.service.getItemDetails(this.itemId).subscribe(data=>{this.item=data; this.productAmount=data.price});
    this.service.getCustomerDetails().subscribe((data: any)=>{
      this.dataObj = JSON.parse(data);
      this.amount = this.dataObj.totalAmount;

    });
  }


  transactionDisplay(){
    if(this.service.isCouponCodeApplied){
      this.productAmount -= this.productAmount * (this.service.discountOnCouponCode / 100);
    }
    this.service.createTransaction(this.productAmount).subscribe(

      (response) => {
        console.log(response);
        
        if(this.amount >= this.productAmount){

          this.openTransactionModel(response);
        }
        else{
          alert("Not sufficient balance");
        }
      },
      (error) => {
        console.log(error);
      }
    );
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
        this.processResponse(response);
      },
      prefill: {
        name: 'Arjun Rawat',
        email: 'arjunrawat2002@gmail.com',
        contact: '7252046127'
      },
      notes:{
        address: '78,Baldevpuri'
      },
      theme: {
        color: 'black'
      }
    };

    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
  }

  processResponse(resp: any){
    console.log(resp);
    this.amount -= this.productAmount;
    this.service.updatePrice(this.amount).subscribe();
    if(this.service.isCouponCodeApplied){
      this.service.deleteCoupon(this.service.couponCodeTORedeem).subscribe();
      this.service.isCouponCodeApplied = false;
      this.service.couponCodeTORedeem = "";
    }
    console.log(this.amount);
    
  }

  addCoupon()
  {
    this.service.addCoupon(this.item.couponCode).subscribe();
    this.router.navigate(["/coupons"]);
  }
  
}
