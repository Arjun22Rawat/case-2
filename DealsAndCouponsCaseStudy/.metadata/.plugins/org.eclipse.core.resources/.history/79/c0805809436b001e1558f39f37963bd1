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
	public Items updateProductPrice(double price, String itemId) {

		Optional<Items> obj = repo.findById(itemId);

		obj.get().setPrice(price);

		return repo.save(obj.get());

	}

	

	public Items updateCouponCodeDiscount(int couponDiscount, String itemId) {

		Optional<Items> obj = repo.findById(itemId);

		obj.get().setCouponDiscount(couponDiscount);

		return repo.save(obj.get());

	}

	

	public Items updateInStock(int totalAvailableQuantity, String itemId) {

		Optional<Items> obj = repo.findById(itemId);

		obj.get().setTotalAvailableQuantity(totalAvailableQuantity);;

		return repo.save(obj.get());

	}

	

	public Items updateDiscount(int discountOnItem, String itemId) {

		Optional<Items> obj = repo.findById(itemId);

		obj.get().setDiscountOnItem(discountOnItem);;

		return repo.save(obj.get());

	}

	

	public void deleteProduct(String itemId) {

		repo.deleteById(itemId);

	}

	public int getDiscountOnCouponCode(String couponCode) {
		// TODO Auto-generated method stub
		return repo.findByCouponCode(couponCode).getCouponDiscount();
//		return 0;
	}

	
}
