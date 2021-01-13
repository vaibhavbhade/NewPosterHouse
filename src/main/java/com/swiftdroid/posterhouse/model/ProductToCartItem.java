package com.swiftdroid.posterhouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ProductToCartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name="product_config_id")
	private ProductConfig productConfig;

	
	@ManyToOne
	@JoinColumn(name="cart_item_id")
	private CartItem cartItem;
	

	public ProductToCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		product = product;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public ProductConfig getProductConfig() {
		return productConfig;
	}

	public void setProductConfig(ProductConfig productConfig) {
		this.productConfig = productConfig;
	}

	
	

	
}
