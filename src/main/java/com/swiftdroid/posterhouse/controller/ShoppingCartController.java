package com.swiftdroid.posterhouse.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.swiftdroid.posterhouse.config.CustomOAuth2User;
import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.CartItemToImage;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.repo.CartItemImageRepository;
import com.swiftdroid.posterhouse.service.CartItemService;
import com.swiftdroid.posterhouse.service.ProductConfigService;
import com.swiftdroid.posterhouse.service.ProductService;
import com.swiftdroid.posterhouse.service.ShoppingCartService;
import com.swiftdroid.posterhouse.service.UserService;
import com.swiftdroid.posterhouse.serviceimpl.CartToImageService;

@Controller
public class ShoppingCartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private Environment env;

	/*
	 * @Autowired private BookService bookService;
	 */
	@Autowired
	private CartItemImageRepository cartItemImageImpl;

	@Autowired
	private CartToImageService cartToImageService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ProductConfigService productConfigService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/cart")
	public String userShoppingCart(Model model, Principal principal, Authentication authentication) {

		User user = null;
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
			String email = oAuth2User.getEmail();
			System.out.println("eeeeeeeeeeeeeeee" + email);
			user = userService.findByEmail(email);
		} catch (Exception e) {
			user = userService.findByUsername(principal.getName());
		} finally {

			ShoppingCart shoppingCart = user.getShoppingCart();

			shoppingCartService.updateShoppingCart(shoppingCart);
			System.out.println("here");

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", shoppingCart);

			return "shoppingCart";

		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/addItem")
	public String addItem(
			@ModelAttribute("productConfig") ProductConfig productConfig, Model model, Principal principal,
			Authentication authentication, @RequestParam("file") MultipartFile[] imageMultipart)
			throws MultipartException {

		if (productConfig.getId() == null) {
			System.out.println("ProductConfig Id Null  Controller ShoppingCartController :: Method addItem :: ");
			return "badRequestPage";
		}
		User user = null;
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
			String email = oAuth2User.getEmail();
			System.out.println("eeeeeeeeeeeeeeee" + email);
			user = userService.findByEmail(email);
		} catch (Exception e) {
			user = userService.findByUsername(principal.getName());
		} finally {

			
			String path=env.getProperty("imgPath");
			
			System.out.println("path :: "+path);
			System.out.println("ProductCofig ID :: " + productConfig.getId());

			System.out.println(
					"*************************************************************11*********************************************");
			productConfig = productConfigService.findProducConfigtByID(productConfig.getId());

			Product product = productService.findProductById(productConfig.getProduct().getId());// product

			System.out.println(
					"*************************************************************22*********************************************");

			System.out.println("yyyy  " + imageMultipart.length);
			System.out.println(product.getMaximumQuantity());
			int quantity = imageMultipart.length;

			int maximumQuantity = product.getMaximumQuantity();

			if (quantity > maximumQuantity) {

				model.addAttribute("notEnoughStock", true);

				return "forward:/productDetail?id=" + product.getId();
			}
			try {

				List<CartItem> cartItemList = user.getShoppingCart().getCartItemList();
				System.out.println("@@@@@@@@@@@ 1 @@@@@@@");
				if (cartItemList.size() != 0) {
					for (CartItem cartItem : cartItemList) {
						System.out.println("@@@@@@@@@@@ 2 @@@@@@@");

						if (cartItem.getProduct().equals(product) && cartItem.getProductConfig().equals(productConfig)) {
							System.out.println("@@@@@@@@@@@ 3 @@@@@@@");

							for (MultipartFile multipartFile : imageMultipart) {
								System.out.println("@@@@@@@@@@@ 3 loop @@@@@@@");

								byte[] bytes = multipartFile.getBytes();

								CartItemToImage cartItemImage = new CartItemToImage();
								cartItemImage.setCartItem(cartItem);
								cartItemImage = cartItemImageImpl.save(cartItemImage);
								String name = user.getShoppingCart().getId() + "_" + product.getId() + "_"
										+ cartItem.getId() + "_" + cartItemImage.getId() + "_.png";
								cartItemImage.setImgPath(name);
								//src/main/resources/static/img/user/userproductImage/
								// String id=user.getShoppingCart().getId() + "_" + product.getId() + "_.png"
								File file = new File(path + name);
								FileOutputStream out = new FileOutputStream(file);
								BufferedOutputStream strem = new BufferedOutputStream(out);
								cartItemImageImpl.save(cartItemImage);
								strem.write(bytes);
								strem.close();
							}

							System.out.println(
									"*************************************************************33*********************************************");
							System.out.println("productConfig::::" + productConfig);

							System.out.println("@@@@@@@@@@@ 4 @@@@@@@");

							CartItem cartItems = cartItemService.addProductToCartItem("", productConfig, product, user,
									imageMultipart.length);

							model.addAttribute("addBookSuccess", true);

							return "forward:/productDetail?id=" + product.getId();

						} else {
							CartItem cartItems = cartItemService.addProductToCartItem("", productConfig, product, user,
									imageMultipart.length);

							System.out.println("@@@@@@@@@@@ 5 @@@@@@@");

							for (MultipartFile multipartFile : imageMultipart) {

								byte[] bytes = multipartFile.getBytes();

								CartItemToImage cartItemImage = new CartItemToImage();
								cartItemImage.setCartItem(cartItems);
								cartItemImage = cartItemImageImpl.save(cartItemImage);
								String name = user.getShoppingCart().getId() + "_" + product.getId() + "_"
										+ cartItems.getId() + "_" + cartItemImage.getId() + "_.png";
								cartItemImage.setImgPath(name);
								// String id=user.getShoppingCart().getId() + "_" + product.getId() + "_.png"
								File file = new File(path + name);
								FileOutputStream out = new FileOutputStream(file);
								BufferedOutputStream strem = new BufferedOutputStream(out);
								cartItemImageImpl.save(cartItemImage);
								strem.write(bytes);
								strem.close();
							}
							model.addAttribute("addBookSuccess", true);
							System.out.println("@@@@@@@@@@@ 3@@@@@@@");
							return "forward:/productDetail?id=" + product.getId();
						}
					}
				} else {

					CartItem cartItems = cartItemService.addProductToCartItem("", productConfig, product, user,
							imageMultipart.length);

					for (MultipartFile multipartFile : imageMultipart) {

						byte[] bytes = multipartFile.getBytes();

						CartItemToImage cartItemImage = new CartItemToImage();
						cartItemImage.setCartItem(cartItems);
						cartItemImage = cartItemImageImpl.save(cartItemImage);
						String name = user.getShoppingCart().getId() + "_" + product.getId() + "_" + cartItems.getId()
								+ "_" + cartItemImage.getId() + "_.png";
						cartItemImage.setImgPath(name);
						// String id=user.getShoppingCart().getId() + "_" + product.getId() + "_.png"
						File file = new File(path + name);
						FileOutputStream out = new FileOutputStream(file);
						BufferedOutputStream strem = new BufferedOutputStream(out);
						cartItemImageImpl.save(cartItemImage);
						strem.write(bytes);
						strem.close();
					}
					model.addAttribute("addBookSuccess", true);
					return "forward:/productDetail?id=" + product.getId();

				}
			}

			catch (Exception e) {
				System.out.println(e.getMessage());

			}

			return "forward:/productDetail?id=" + product.getId();
		}
	}

	@RequestMapping("/updateCartItem")
	public String updateShoppingCart(@ModelAttribute("id") Long cartItemId, @ModelAttribute("qty") int qty,
			Model model) {
		try {

			if (qty == 0) {

				model.addAttribute("qtyMsg", true);
				return "forward:/cart";
			}
			System.out.println("cartItemId::" + cartItemId);
			CartItem cartItem = cartItemService.findById(cartItemId);
			cartItem.setQty(qty);
			cartItemService.updateCartItem(cartItem);

			return "redirect:/cart";
		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("id  null In ShoppingCartController   Method:: updateShoppingCart  ::  " + e.getMessage());
			return "badRequestPage";
		}

	}

	@SuppressWarnings("finally")
	@RequestMapping("/removeItem")
	public String removeItem(@RequestParam("id") Long id, @RequestParam("count") Long imageId,
			Authentication authentication, Principal principal, Model model) {
		System.out.println("id  ::  " + id);
		System.out.println("id  ::  " + imageId);

		if (id == null) {

			System.out.println("id  null In ShoppingCartController   Method:: removeItem  ::  ");
			return "badRequestPage";

		}
		try {
			User user = null;
			try {
				CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
				String email = oAuth2User.getEmail();
				System.out.println("eeeeeeeeeeeeeeee" + email);
				user = userService.findByEmail(email);
			} catch (Exception e) {
				user = userService.findByUsername(principal.getName());
			} finally {

				CartItem cartItem = cartItemService.findById(id);

				if (cartItem.getQty() == 0) {
					model.addAttribute("qtyMsg", true);
					cartItemService.removeCartItem(cartItemService.findById(id));
					return "redirect:/cart";
				}

				Product product = cartItem.getProduct();
				ShoppingCart shoppingCart = user.getShoppingCart();
				String name = user.getShoppingCart().getId() + "_" + product.getId() + "_" + cartItem.getId() + "_"
						+ imageId + "_.png";
				Files.delete(Paths.get("src/main/resources/static/img/user/userproductImage/" + name));
				cartToImageService.deleteCartImageByCartIdAndCartImageId(cartItem, imageId);
				cartItem.setQty(cartItem.getQty() - 1);
				cartItemService.saveCart(cartItem);
				return "redirect:/cart";

			}
		} catch (Exception e) {
			System.out.println("id  null In ShoppingCartController   Method:: removeItem  ::  " + e.getMessage());
			return "badRequestPage";
		}
	}

}
