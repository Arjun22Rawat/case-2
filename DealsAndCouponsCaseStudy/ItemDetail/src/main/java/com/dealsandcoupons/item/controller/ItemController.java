package com.dealsandcoupons.item.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dealsandcoupons.item.model.Items;

import com.dealsandcoupons.item.service.ItemService;

import jakarta.validation.Valid;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/itemService")
public class ItemController 
{
	@Autowired 
	private ItemService service;
	
	@PostMapping("/addItem")

	public ResponseEntity<String> addItem(@Valid @RequestParam("itemId") String itemId,@RequestParam("itemName") String itemName,@RequestParam("price") double price,@RequestParam("couponCode") String couponCode,@RequestParam("couponDiscount") int couponDiscount,@RequestParam("file") MultipartFile file,@RequestParam("rating") int rating,@RequestParam("description") String description,@RequestParam("totalAvailableQuantity") int totalAvailableQuantity,@RequestParam("discountOnItem") int discountOnItem) throws IOException

	{
		try {
            String result = service.addItem(itemId, itemName, price, couponCode, couponDiscount, file, rating, description, totalAvailableQuantity, discountOnItem);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item: " + e.getMessage());
        }
	}

	@PostMapping("/getProductsByAdmin")
	public ResponseEntity<String> addProductsByAdmin(@Valid  @RequestBody Items item) {
		try {

            String result = service.addProductsByAdmin(item);

            return ResponseEntity.ok(result);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item by admin: " + e.getMessage());

        }
	}
	
	

	
	
	@GetMapping("/getItemById/{itemId}")
	public ResponseEntity<Items> getItemById(@PathVariable String itemId) {
		Items item = service.getItemById(itemId);

        if (item != null) {

            return ResponseEntity.ok(item);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
	}
	
	@GetMapping("/getAllItem")

		public ResponseEntity<List<Items>> getAllItem() {

			List<Items> items = service.getAllItem();

	        if (items.isEmpty()) {

	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	        } else {

	            return ResponseEntity.ok(items);

	        }

		}
	
	
	
	@PutMapping("/updateProductPrice/{itemId}/{price}/{couponDiscount}")

	public ResponseEntity<Items> updateProductPrice(  @PathVariable String itemId,@PathVariable("price") double price, @PathVariable("couponDiscount") int couponDiscount) {

		try {

            Items updatedItem = service.updateProductPrice( itemId,price,couponDiscount);

            return ResponseEntity.ok(updatedItem);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

	}
	


	@DeleteMapping("/deleteProduct/{itemId}")

	public ResponseEntity<String> deleteProduct(@PathVariable String itemId) {

 

		try {

            service.deleteProduct(itemId);

            return ResponseEntity.ok("Item deleted successfully.");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found: " + e.getMessage());

        }

	}

	

	@GetMapping("/getDiscountOnCouponCode/{couponCode}")

	public ResponseEntity<Integer> getDiscountOnCouponCode(@PathVariable String couponCode) {

		Integer discount = service.getDiscountOnCouponCode(couponCode);

        if (discount != null) {

            return ResponseEntity.ok(discount);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);

        }

	}
	
	 @GetMapping("/checkItemById/{itemId}")
		public Items checkItemById(@PathVariable String itemId) {
			return service.checkItemById(itemId);
	        
		}
	
	
	
}