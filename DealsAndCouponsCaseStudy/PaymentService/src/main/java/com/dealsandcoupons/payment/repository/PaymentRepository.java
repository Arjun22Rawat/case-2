package com.dealsandcoupons.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dealsandcoupons.payment.model.Payment;
import com.dealsandcoupons.payment.model.PaymentRecord;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentRecord, String> {

}
