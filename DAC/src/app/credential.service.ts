import { Route, Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retryWhen } from 'rxjs';
import { User } from './user';
import { Items } from './items';
import { Cart } from './cart';
import { CustomerDetail } from './customer-detail';

@Injectable({
  providedIn: 'root'
})
export class CredentialService {
  public userObj:User = new User();
  item:Items=new Items();
  isLogin:any = false;
  custId:any;
  token: any;

  public discountOnCouponCode = 0;
  public isCouponCodeApplied = false;
  public couponCodeTORedeem = "";
  private _urlRegister = "http://localhost:8080/loginService/register";
  private _urlLogin = "http://localhost:8080/loginService/loginForm";
  private _urlgetAllItem = "http://localhost:8082/itemService/getAllItem";
  private _urlAddToCart = "http://localhost:8085/gatewayService/getItemFromId/";
  private _urlCartTotal = "http://localhost:8085/gatewayService/findCartTotal/";
  private _urlAddItemToCart= "http://localhost:8084/cartService/addItemInCart";
  private _urlAddCustomer= "http://localhost:8084/cartService/addCustomer/";
  private _urlItemDetails = "http://localhost:8082/itemService/getItemById/";
  private _urlcreateTransaction = "http://localhost:8086/paymentService/createTransaction/"
  private _urladdCoupon = "http://localhost:8081/customerService/addCoupon/"
  private _urlgetCoupon = "http://localhost:8081/customerService/getAllCoupons/"
  private _urlgetCustomerDetails = "http://localhost:8081/customerService/getDetailsById/"
  private _urladdCustomerDetails = "http://localhost:8081/customerService/addDetails"
  private _urlForDiscountOnCouponCode = "http://localhost:8082/itemService/getDiscountOnCouponCode/";
  private _urlForUpdatePrice = "http://localhost:8081/customerService/updatePrice/";
  private _urlForDeleteCoupon = "http://localhost:8081/customerService/removeCoupon/";
  private _urlSendEmail = "http://localhost:8080/loginService/sendVerificationCode"; 
  private _urlVerifyEmail = "http://localhost:8080/loginService/verifyAccount/"; 
  private _urlGenerateToken = "http://localhost:8080/loginService/generatetoken";
  private _urlTopDeals="http://localhost:8082/itemService/getTopDeal";
  private _urlCheckUsersExist="http://localhost:8080/loginService/getUsersById";
  private _urlProductByAdmin="http://localhost:8082/itemService/getProductsByAdmin";

  private _urlDeleteProductByAdmin="http://localhost:8082/itemService/deleteProduct";

  private _urlGetUserById="http://localhost:8080/loginService/getUsersById/";


  constructor(private http:HttpClient) 
  { }

  register(user:User):Observable<User>
  {
    return this.http.post<User>(`${this._urlSendEmail}`,user);

  }
  loginForm(user:User):Observable<User>
  {
    this.userObj.emailId = user.emailId;
    return this.http.post<User>(`${this._urlLogin}`,user);
  }
  getAllItems():Observable<Items[]>
  {
    return this.http.get<Items[]>(`${this._urlgetAllItem}`);
  }

  getTopDeals(): Observable<Items[]> {
    return this.http.get<Items[]>(`${this._urlTopDeals}`);
  }



  getItemToCart():Observable<Items[]>
  {
    // this.custId = this.userObj.emailId;
    return this.http.get<Items[]>(`${this._urlAddToCart}${this.userObj.emailId}`);
  }
  cartTotal():Observable<number>
  {
    this.custId = this.userObj.emailId;
    return this.http.get<number>(`${this._urlCartTotal}${this.userObj.emailId}`);
  }

  addItemToCart(cart:Cart):Observable<any>
  {
      cart.customerId = this.userObj.emailId;
      return this.http.post(`${this._urlAddItemToCart}`,cart);
  }

  addCustomerInCart(id:any):Observable<any>
  {

    return this.http.get(`${this._urlAddCustomer}${id}`);
  }

  getItemDetails(id:any):Observable<any>
  {

      return this.http.get(`${this._urlItemDetails}${id}`);
  }
  createTransaction(amount:any):Observable<any>
  {
    return this.http.get(`${this._urlcreateTransaction}${amount}`);
  }
  addCoupon(code:any):Observable<any>
  {
    return this.http.get(this._urladdCoupon+this.userObj.emailId+"/"+code);
  }
  getAllCoupons():Observable<any[]>
  {
    console.log(this.userObj.emailId);
    return this.http.get<any[]>(this._urlgetCoupon+this.userObj.emailId);
  }
  getCustomerDetails():Observable<any>
  {
    let tokenStr = 'Bearer ' + this.token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.http.get<any>(this._urlgetCustomerDetails+this.userObj.emailId, {headers, responseType:'text' as 'json'});
  }
  addCustomerDetails(cust:CustomerDetail):Observable<any>
  {
    console.log(cust);
    return this.http.post<any>(this._urladdCustomerDetails,cust);
  }
  getDiscountFromCouponCode(couponCode: any): Observable<any>{
    return this.http.get<any>(this._urlForDiscountOnCouponCode + couponCode);
  }

  updatePrice(price: any): Observable<any>{
    return this.http.get<any>(this._urlForUpdatePrice + this.userObj.emailId + "/" + price);
  }
  deleteCoupon(couponCode: any): Observable<any>{
    return this.http.get<any>(this._urlForDeleteCoupon + this.userObj.emailId + "/" + couponCode);
  }

  verifyEmail(token:any):Observable<any>
  {
    return this.http.get<any>(this._urlVerifyEmail+token);
  }
  generateToken(user:User):Observable<any>
  {
    return this.http.post<any>(this._urlGenerateToken,user);
  }
  checkUsersExists(email: string): Observable<boolean> {

    return this.http.get<boolean>(`${this._urlCheckUsersExist}/${email}`);

  }
addProductsInDb(item: Items):Observable<any>{
  return this.http.post<any>(this._urlProductByAdmin,item)
}

deleteProductByAdmin(itemId:any):Observable<any>{
  
  return this.http.get<any>(this._urlDeleteProductByAdmin+"/"+itemId);
}
getUserById():Observable<any>

  {

    let tokenStr = 'Bearer ' + this.token;

    const headers = new HttpHeaders().set("Authorization", tokenStr);

    return this.http.get<any>(this._urlGetUserById+this.userObj.emailId, {headers, responseType:'text' as 'json'});

  }

}
