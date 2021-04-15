package com.swiftdroid.posterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItemToImage {

@Id
@GeneratedValue
private Long id;

private String imgPath;

@ManyToOne
@JoinColumn(name="cart_id")
private CartItem cartItem;

public Long getId() {
	return id;
}

public void setId(Long id) {
	id = id;
}

public String getImgPath() {
	return imgPath;
}

public void setImgPath(String imgPath) {
	this.imgPath = imgPath;
}

public CartItem getCartItem() {
	return cartItem;
}

public void setCartItem(CartItem cartItem) {
	this.cartItem = cartItem;
}
	
	

	
	
}
