import { Router, ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { PaymentRecord } from '../payment-record';

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
  paymentRecord: PaymentRecord = new PaymentRecord();

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
    let prodAmount = this.productAmount;
    if(this.service.isCouponCodeApplied){
      prodAmount = 0;
      prodAmount = this.productAmount - this.productAmount * (this.service.discountOnCouponCode / 100);
    }
    this.service.createTransaction(prodAmount).subscribe(

      (response) => {
        console.log(response);

       
          if(this.service.userObj.emailId== null)
          {
            this.router.navigate(["/login"]);
          }
         
        else{
       
        this.openTransactionModel(response);
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
      itemName: response.itemName,
      name: 'Arjun',
      description: 'Payment',
      image: 'C:\Users\MicroSoft\Pictures\Screenshots.png',
      handler: (response: any) => {
        this.processResponse(response);
        this.router.navigate(["/home"]);
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
    console.log(resp);
    this.paymentRecord.email = this.service.userObj.emailId;
    this.paymentRecord.amount = this.amount;
    this.paymentRecord.orderId = resp.razorpay_payment_id;
    this.paymentRecord.productName = this.item.itemName;

    this.service.addPaymentDetails(this.paymentRecord).subscribe();
  }

  redeemCoupon(itemId:any)
  {
    this.service.addCoupon(this.item.couponCode).subscribe();
    this.router.navigate(["/coupons",itemId]);
  }
  
}
