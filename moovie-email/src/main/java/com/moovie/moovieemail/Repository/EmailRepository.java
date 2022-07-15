package com.moovie.moovieemail.Repository;

import com.moovie.moovieemail.Model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, Long> {
}
