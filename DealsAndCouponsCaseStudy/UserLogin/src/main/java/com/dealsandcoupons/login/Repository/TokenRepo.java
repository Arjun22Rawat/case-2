package com.dealsandcoupons.login.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealsandcoupons.login.model.ConfirmationToken;

@Repository
public interface TokenRepo extends MongoRepository<ConfirmationToken,String>
{

}
