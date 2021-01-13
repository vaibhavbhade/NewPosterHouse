package com.swiftdroid.posterhouse.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.ProductToCartItem;

@Transactional
public interface ProductToCartItemRepository extends CrudRepository<ProductToCartItem, Long>{

	public void deleteByCartItem(CartItem cartItem);
		
	

}
