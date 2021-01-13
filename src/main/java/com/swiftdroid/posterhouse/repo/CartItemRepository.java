package com.swiftdroid.posterhouse.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.ShoppingCart;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
