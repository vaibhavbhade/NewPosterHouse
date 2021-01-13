package com.swiftdroid.posterhouse.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findOrderByUser(User user);
}
