package com.moovie.mooviepayment.Repository;

import com.moovie.mooviepayment.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Long> {
}
