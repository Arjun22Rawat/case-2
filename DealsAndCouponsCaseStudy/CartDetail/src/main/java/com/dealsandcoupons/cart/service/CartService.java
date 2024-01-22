package com.dealsandcoupons.cart.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealsandcoupons.cart.model.Cart;
import com.dealsandcoupons.cart.repository.CartRepository;

@Service
public class CartService 
{
	@Autowired
	private CartRepository repo;

	public void addItemInCart(Cart cart) 
	{
		// TODO Auto-generated method stub
		
		Optional<Cart> obj = repo.findById(cart.getCustomerId());
		if(obj.isEmpty())
		{
			HashMap<String,Integer> map = new HashMap<>();
			map.put(cart.getItemId(),1);
			cart.setItemDetails(map);
			repo.save(cart);
		}
		else
		{
			HashMap<String,Integer> map = obj.get().getItemDetails();
			if(map.containsKey(cart.getItemId()))
			{
				map.put(cart.getItemId(), obj.get().getItemDetails().get(cart.getItemId())+1);
			}
			else
			{
				map.put(cart.getItemId(),1);
			}	
			cart.setItemDetails(map);
			repo.save(cart);
		}
	}
	public void addCustomer(String customerId) {
		Cart cartObj = new Cart();
		cartObj.setCustomerId(customerId);
		cartObj.setItemDetails(new HashMap<String, Integer>());
		repo.save(cartObj);
	}
	public HashMap<String,Integer> getItemFromCart(String customerId) 
	{
		// TODO Auto-generated method stub
		Optional<Cart> cart = repo.findById(customerId);
		return cart.get().getItemDetails();
	}
	
	public boolean deleteUser(String custID)
	{
		 repo.deleteById(custID);
		 return true;
	}

	
	public void deleteItemFromCart(String customerId, String itemId) 
	{
		// TODO Auto-generated method stub
		Optional<Cart> cart = repo.findById(customerId);
		 HashMap<String, Integer> map =cart.get().getItemDetails();
//		if(map.get(itemId)==1)
//		{
//			map.remove(itemId);
//		}
//		else if(map.get(itemId)>1)
//		{
//			map.put(itemId, map.get(itemId)-1);
//		}
		 Optional<Cart> cartOptional = repo.findById(customerId);

		    if (cartOptional.isPresent()) {
		        Cart cart1 = cartOptional.get();
		        HashMap<String, Integer> itemDetails = cart1.getItemDetails();

		        if (itemDetails.containsKey(itemId)) {
		            int itemCount = itemDetails.get(itemId);

		            // If the item count is 1, remove it from the cart
		            if (itemCount == 1) {
		                itemDetails.remove(itemId);
		            } else if (itemCount > 1) {
		                // If the item count is greater than 1, decrement the count
		                itemDetails.put(itemId, itemCount - 1);
		            }

		            // Save the updated cart to the repository
		            repo.save(cart1);
		        } else {
		            // Handle the case where the item is not in the cart
		            // You might want to throw an exception or handle it differently
		        }
		    } else {
		        // Handle the case where the customer's cart is not found
		        // You might want to throw an exception or handle it differently
		    }
	}
}
