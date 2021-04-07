package com.swiftdroid.posterhouse.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;
import com.swiftdroid.posterhouse.model.ProductToCartItem;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.repo.CartItemRepository;
import com.swiftdroid.posterhouse.repo.ProductToCartItemRepository;
import com.swiftdroid.posterhouse.service.CartItemService;

@Service
public class CartItemServiceImpl  implements CartItemService
 {
	
	@Autowired
	private CartItemRepository  cartItemRepository;

	@Autowired
	private ProductToCartItemRepository  productToCartItemRepository;
	
	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal=new BigDecimal(cartItem.getProductConfig().getPricePerQty()).multiply(new BigDecimal(cartItem.getQty()));
		BigDecimal finalbigDecimal=new BigDecimal(00);
		bigDecimal =bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
		 cartItem.setSubtotal(bigDecimal);
          cartItemRepository.save(cartItem);
		 return cartItem;
	}

	@Override
	public CartItem addProductToCartItem(String path,ProductConfig productConfig,Product product, User user, int qty) {
		
		  List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
			
			for (CartItem cartItem : cartItemList) {
				if(productConfig.getId() == cartItem.getProductConfig().getId()) {
					cartItem.setQty(qty);
					cartItem.setSubtotal(new BigDecimal(productConfig.getPricePerQty()).multiply(new BigDecimal(qty)));
					cartItemRepository.save(cartItem);
					return cartItem;
				}
			}
			
			System.out.println("*************************************************************in method11*********************************************");	
 
			 System.out.println("pppppp1111"+product.getId());
			 
			CartItem cartItem = new CartItem();
			
			cartItem.setShoppingCart(user.getShoppingCart());
			cartItem.setSize(productConfig.getSize());
	        cartItem.setProduct(product);
			cartItem.setUserImagePath(path);
		    cartItem.setProductConfig(productConfig);
			cartItem.setQty(qty);
			cartItem.setSubtotal(new BigDecimal(productConfig.getPricePerQty()).multiply(new BigDecimal(qty)));
			
			
			        
			cartItem = cartItemRepository.save(cartItem);
            
			ProductToCartItem productToCartItem=new ProductToCartItem();
			
			 productToCartItem.setProductConfig(productConfig);
			 
             productToCartItem.setProduct(product);

			System.out.println("product in adddddto cart****************:: "+product);
			

			 productToCartItem.setCartItem(cartItem);
			 
			productToCartItemRepository.save(productToCartItem);
			
			return cartItem;

	}

	@Override
	public CartItem findById(Long cartItemId) {
		// TODO Auto-generated method stub
		return cartItemRepository.findById(cartItemId).orElse(null);
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		productToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.delete(cartItem);
	}

	@Override
	public void saveCart(CartItem cartItem) {
		// TODO Auto-generated method stub
		cartItemRepository.save(cartItem);
	}

	@Override
	public List<CartItem> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByOrder(order);
	}

}
