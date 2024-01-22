import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';
import { Items } from '../items';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
// export class CouponsComponent 
// {

//   items:Items[] = [];
//   couponList:any[]=[];

//   constructor(private service:CredentialService, private router:Router)
//   {

//   }

//   ngOnInit()
//   {
//     this.service.getAllItems().subscribe(data=>{
//       this.items=data;
//       //this.topDeals = data
//     });
//     this.service.getAllCoupons().subscribe(data=>this.couponList=data);
//   }
//   redeem(item:Items)
//   {
//     this.service.getDiscountFromCouponCode(item.couponCode).subscribe(data=>{
//       this.service.discountOnCouponCode = data;
//       this.service.isCouponCodeApplied = true;
//       this.service.couponCodeTORedeem = item.couponCode;
      
//       this.router.navigate(["/details",item.itemId]);
//       alert("Coupon added succesfully");
//     });
//   }
// }
export class CouponsComponent
{
 
  items:Items[] = [];
  couponList:any[]=[];
  itemId:any;
  constructor(private service:CredentialService, private router:Router,private route:ActivatedRoute)
  {
 
  }
 
  ngOnInit()
  {
    this.itemId = this.route.snapshot.paramMap.get("id");
    this.service.getAllItems().subscribe(data=>{
      this.items=data;
      //this.topDeals = data
      console.log(this.items);
     
     
    });
    this.service.getAllCoupons().subscribe((data)=>{this.couponList=data; console.log(this.couponList);
    });
  }
  redeem(coupon:any)
  {
 
    this.service.getDiscountFromCouponCode(coupon).subscribe(data=>{
      this.service.discountOnCouponCode = data;
      this.service.isCouponCodeApplied = true;
      this.service.couponCodeTORedeem = coupon;
      this.router.navigate(["/details",this.itemId]);
      alert("Coupon added succesfully");
    });
  }
}
