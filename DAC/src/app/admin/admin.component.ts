import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

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
  
 

  constructor(private detailService: CredentialService) {}

 

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
  
  deleteProductByAdmin(){
    this.detailService.deleteProductByAdmin(this.item.itemId).subscribe();
    alert("Item Deleted successfully");
  }

}
