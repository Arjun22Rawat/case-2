import { Route, Router } from '@angular/router';
import { Component } from '@angular/core';
import { CredentialService } from '../credential.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent 
{
  couponList:any[]=[];
  constructor(private service:CredentialService, private router:Router)
  {

  }

  ngOnInit()
  {
    this.service.getAllCoupons().subscribe(data=>this.couponList=data);
  }
  redeem(coupon:any)
  {
    this.service.getDiscountFromCouponCode(coupon).subscribe(data=>{
      this.service.discountOnCouponCode = data;
      this.service.isCouponCodeApplied = true;
      this.service.couponCodeTORedeem = coupon;
      this.router.navigate(["/home"]);
    });
  }
}
