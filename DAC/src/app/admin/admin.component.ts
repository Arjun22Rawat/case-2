import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { CredentialService } from '../credential.service';

import { Items } from '../items';
import { Cart } from '../cart';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  item: Items = new Items();
  itemId: any;
  price: any;
  couponDiscount: any;
  couponError:any;
  isUpdateDisabled : boolean=true;
 

  constructor (private detailService: CredentialService, private router:Router) {}

  ngOnInit()
  {
    // if(localStorage.getItem("token")!=null){
    //   this.router.navigate(["/login"])
    // }
  }


  checkItemId(){
    this.detailService.checkItemsExists(this.item.itemId).subscribe((itemExists: boolean) => {
     if (itemExists) {
       alert('Item is already exists!');
     } else {
       this.addProductToDB();
     }
   });
 }

  addProductToDB(){

    console.log(this.item);

    this.detailService.addProductsInDb(this.item).subscribe();
    this.item= new Items();
   alert("Item Added successfully");
    
    


  }

 

  onFileSelected(event: any){

    console.log("jkkhajn")

    const file = event.target.files[0];

    if(file){

      this.convertImageToByteArray(file);

    }

  }

 

  convertImageToByteArray(file: File){

    const reader = new FileReader();

    reader.onload = (e: any) => {

      console.log("hello");

      const dataUrl = e.target.result;

      this.item.image = dataUrl.split(',')[1];

    };

    reader.readAsDataURL(file);

  }
  
  checkItemIdforDelete(){
    this.detailService.checkItemsExists(this.item.itemId).subscribe((itemExists: boolean) => {
     if (itemExists) {
       this.deleteProductByAdmin();
     } else {
       alert('Item does not exists!');
     }
   });
 }

  deleteProductByAdmin(){
    this.detailService.deleteProductByAdmin(this.item.itemId).subscribe();
    alert("Item Deleted successfully");
  }
  validateCouponDiscount() {
    if (this.couponDiscount === null || isNaN(this.couponDiscount)) {
      this.couponError = 'Please enter a valid number for Coupon Discount.';
    } else if (this.couponDiscount < 0 || this.couponDiscount > 100) {
      this.couponError = 'Coupon Discount should be between 0 and 100.';
    } else {
      this.couponError = ''; // Reset error message
      this.isUpdateDisabled = false; // Enable the button
      console.log('Valid Coupon Discount:', this.couponDiscount);
      // Perform other operations like updating items, etc.
    }
  }

  checkId(){
    this.detailService.checkItemsExists(this.itemId).subscribe((itemExists: boolean) => {
     if (itemExists) {
      this.updateItems();
       
     } else {
      alert('Item does not exists!');
     }
   });
 }

  updateItems(){
    console.log(this.price);
    this.detailService.updateItemDetails(this.itemId,this.price,this.couponDiscount).subscribe();
    this.item=new Items();
    alert("Item Detail Updated successfully");
  }

}
