package com.swiftdroid.posterhouse.serviceimpl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.BillingAddress;
import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;
import com.swiftdroid.posterhouse.model.ShippingAddress;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.repo.OrderRepository;
import com.swiftdroid.posterhouse.service.CartItemService;
import com.swiftdroid.posterhouse.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public synchronized Order   createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			String shippingMethod, User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		//order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Product product = cartItem.getProduct();
			ProductConfig productConfig=cartItem.getProductConfig();
			cartItem.setOrder(order);
			product.setMaximumQuantity(product.getMaximumQuantity() - cartItem.getQty());
		}
		
		  order.setCartItemList(cartItemList);
		  order.setOrderDate(new Date());
		  order.setOrderTotal(shoppingCart.getGrandTotal());
		  order.setFinalPrice(shoppingCart.getFinalShippingPriceTotal());
		  order.setCreatedDate(new Date());
			LocalDate today = LocalDate.now();
			order.setEstimateDate(today.plusDays(7));

		  shippingAddress.setOrder(order);
		  billingAddress.setOrder(order);
		//payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;

	}

	@Override
	public List<Order> findOrderByUser(User user) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderByUser(user);
	}

	@Override
	public Order findOrderById(Long Id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(Id).orElse(null);
	}

	@Override
	@Transactional

	public void deleteOrderById(Order order) {
		// TODO Auto-generated method stub
	orderRepository.delete(order);
	}

}
