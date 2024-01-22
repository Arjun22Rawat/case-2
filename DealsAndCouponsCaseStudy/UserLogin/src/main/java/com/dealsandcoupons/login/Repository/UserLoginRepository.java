package com.dealsandcoupons.login.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealsandcoupons.login.model.User;
@Repository
public interface UserLoginRepository extends MongoRepository<User,String>
{
	
}
