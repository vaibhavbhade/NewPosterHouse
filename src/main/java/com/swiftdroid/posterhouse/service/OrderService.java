package com.swiftdroid.posterhouse.service;

import java.util.List;

import com.swiftdroid.posterhouse.model.BillingAddress;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.ShippingAddress;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			String shippingMethod, User user);
	
	List<Order> findOrderByUser(User user);
	
	Order findOrderById(Long Id);
	
	void deleteOrderById(Order order);


}
