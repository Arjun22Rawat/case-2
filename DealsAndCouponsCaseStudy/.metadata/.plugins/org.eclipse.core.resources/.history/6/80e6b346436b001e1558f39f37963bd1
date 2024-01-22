package com.dealsandcoupons.item.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dealsandcoupons.item.model.Items;
import com.dealsandcoupons.item.service.ItemService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/itemService")
public class ItemController 
{
	@Autowired 
	private ItemService service;
	
	@PostMapping("/addItem")
	public String addItem(@RequestParam("itemId") String itemId,@RequestParam("itemName") String itemName,@RequestParam("price") double price,@RequestParam("couponCode") String couponCode,@RequestParam("couponDiscount") int couponDiscount,@RequestParam("file") MultipartFile file,@RequestParam("rating") int rating,@RequestParam("description") String description,@RequestParam("totalAvailableQuantity") int totalAvailableQuantity,@RequestParam("discountOnItem") int discountOnItem) throws IOException
	{
		return service.addItem(itemId,itemName,price,couponCode,couponDiscount,file,rating,description,totalAvailableQuantity,discountOnItem);
	}
	@GetMapping("/getItemById/{itemId}")
	public Items getItemById(@PathVariable String itemId) {
		return service.getItemById(itemId);
	}
	@GetMapping("/getAllItem")
	public List<Items> getAllItem() {
		// TODO Auto-generated method stub
		return service.getAllItem();
	}
	@GetMapping("/updateProductPrice/{itemId}/{price}")
	public Items updateProductPrice(@PathVariable("price") double price, @PathVariable String itemId) {
		return service.updateProductPrice(price, itemId);
	}
	@PutMapping("/updateCouponCodeDiscount/{itemId}")

	public Items updateCouponCodeDiscount(@RequestParam("couponDiscount") int couponDiscount, @PathVariable String itemId) {

		return service.updateCouponCodeDiscount(couponDiscount, itemId);

	}

	

	@PutMapping("/updateInStock/{itemId}")

	public Items updateInStock(@RequestParam("inStock") int totalAvailableQuantity, @PathVariable String itemId) {

		return service.updateInStock(totalAvailableQuantity, itemId);

	}

	

	@PutMapping("/updateDiscount/{itemId}")

	public Items updateDiscount(@RequestParam("discountOnItem") int discountOnItem, @PathVariable String itemId) {

		return service.updateDiscount(discountOnItem, itemId);

	}
	@DeleteMapping("/deleteProduct/{itemId}")

	public void deleteProduct(@PathVariable String itemId) {

		service.deleteProduct(itemId);
	}
	@GetMapping("/getDiscountOnCouponCode/{couponCode}")
	public int getDiscountOnCouponCode(@PathVariable String couponCode) {
		return service.getDiscountOnCouponCode(couponCode);
	}
	
}