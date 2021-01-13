package com.swiftdroid.posterhouse.service;

import com.swiftdroid.posterhouse.model.ShoppingCart;

public interface ShoppingCartService {
 
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

	void clearShoppingCart(ShoppingCart shoppingCart);
	
}
