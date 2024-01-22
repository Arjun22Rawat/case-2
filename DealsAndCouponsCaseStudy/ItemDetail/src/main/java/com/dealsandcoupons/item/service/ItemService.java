package com.dealsandcoupons.item.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dealsandcoupons.item.model.Items;

import com.dealsandcoupons.item.repository.ItemsRepository;


@Service
public class ItemService 
{
	@Autowired 
	private ItemsRepository repo;
	


	public String addItem(String itemId,String itemName,double price,String couponCode,int couponDiscount,MultipartFile file, int rating, String description,int totalAvailableQuantity, int discountOnItem) throws IOException
	{
		Items item = new Items();
		item.setItemId(itemId);
		item.setItemName(itemName);
		item.setPrice(price);
		item.setCouponCode(couponCode);
		item.setCouponDiscount(couponDiscount);
		item.setImage(file.getBytes());
		item.setRating(rating);
		item.setDescription(description);
		item.setTotalAvailableQuantity(totalAvailableQuantity);
		item.setDiscountOnItem(discountOnItem);
		repo.save(item);
		return "Item added in database";
	}

	public Items getItemById(String itemId) {
		// TODO Auto-generated method stub
		return repo.findById(itemId).get();
//		return null;
	}
	public List<Items> getAllItem() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	
	
//	
//	public Items updateProductPrice( String itemId,double price,int discount) {
//
//		Optional<Items> obj = repo.findById(itemId);
//
//		Items itemsObj = obj.get();
//		itemsObj.setPrice(price);
//		itemsObj.setCouponDiscount(discount);
//
//		return repo.save(itemsObj);
//
//	}

	

	public void deleteProduct(String itemId) {

		repo.deleteById(itemId);

	}

	public int getDiscountOnCouponCode(String couponCode) {
		// TODO Auto-generated method stub
		return repo.findByCouponCode(couponCode).getCouponDiscount();
//		return 0;
	}
	public String addProductsByAdmin(Items item) {
		// TODO Auto-generated method stub
		repo.save(item);
		return "Item added successfully";
	}
	public Items checkItemById(String itemId) {
		// TODO Auto-generated method stub
		Items obj = repo.findById(itemId).orElse(null);
		return obj;
	}

	public Items updateProductPrice(String itemId, double price, int discount) {
		// TODO Auto-generated method stub
		Optional<Items> obj = repo.findById(itemId);
		
				Items itemsObj = obj.get();
				itemsObj.setPrice(price);
				itemsObj.setCouponDiscount(discount);
		
				return repo.save(itemsObj);
	}
	
}
