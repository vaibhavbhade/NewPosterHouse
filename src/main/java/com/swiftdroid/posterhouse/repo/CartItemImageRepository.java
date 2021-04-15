package com.swiftdroid.posterhouse.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.CartItemToImage;

public interface CartItemImageRepository extends CrudRepository<CartItemToImage, Long> {
	@Transactional
	int deleteByCartItemAndId(CartItem cartItem,Long id);
}
	