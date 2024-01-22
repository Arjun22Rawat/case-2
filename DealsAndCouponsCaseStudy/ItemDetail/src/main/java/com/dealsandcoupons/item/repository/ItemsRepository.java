package com.dealsandcoupons.item.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealsandcoupons.item.model.Items;

@Repository
public interface ItemsRepository extends MongoRepository<Items,String>{

	public Items findByCouponCode(String couponCode);

}
