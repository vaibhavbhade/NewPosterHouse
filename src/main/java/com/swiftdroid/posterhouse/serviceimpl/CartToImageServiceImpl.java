package com.swiftdroid.posterhouse.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.CartItemToImage;
import com.swiftdroid.posterhouse.repo.CartItemImageRepository;


@Service
public class CartToImageServiceImpl implements CartToImageService {

	@Autowired
	private CartItemImageRepository cartItemImageRepository;
	
	@Override
	public int deleteCartImageByCartIdAndCartImageId(CartItem cartItem,Long id) {
		// TODO Auto-generated method stub
		return cartItemImageRepository.deleteByCartItemAndId(cartItem, id);
	}

	@Override
	public CartItemToImage saveImage(CartItemToImage CartItemToImage) {
		// TODO Auto-generated method stub
		return cartItemImageRepository.save(CartItemToImage);
	}

}
