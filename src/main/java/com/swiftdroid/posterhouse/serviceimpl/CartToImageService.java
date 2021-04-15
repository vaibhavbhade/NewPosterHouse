package com.swiftdroid.posterhouse.serviceimpl;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.CartItemToImage;

public interface CartToImageService {


int deleteCartImageByCartIdAndCartImageId(CartItem cartItem, Long id);
CartItemToImage saveImage(CartItemToImage CartItemToImage);
	
}
