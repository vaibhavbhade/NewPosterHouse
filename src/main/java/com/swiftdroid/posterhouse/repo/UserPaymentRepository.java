package com.swiftdroid.posterhouse.repo;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.UserPayment;

public interface UserPaymentRepository  extends CrudRepository<UserPayment, Long>{

}
