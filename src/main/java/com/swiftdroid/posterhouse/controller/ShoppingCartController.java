package com.swiftdroid.posterhouse.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.service.CartItemService;
import com.swiftdroid.posterhouse.service.ProductConfigService;
import com.swiftdroid.posterhouse.service.ProductService;
import com.swiftdroid.posterhouse.service.ShoppingCartService;
import com.swiftdroid.posterhouse.service.UserService;

@Controller
public class ShoppingCartController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	/*
	 * @Autowired private BookService bookService;
	 */

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

			List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

			shoppingCartService.updateShoppingCart(shoppingCart);

			model.addAttribute("cartItemList", cartItemList);
			model.addAttribute("shoppingCart", shoppingCart);

			return "shoppingCart";

		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/addItem")
	public String addItem(@ModelAttribute("qty") String qty,
			@ModelAttribute("productConfig") ProductConfig productConfig,

			Model model, Principal principal, Authentication authentication,
			@RequestParam("file") MultipartFile[] imageMultipart) throws MultipartException {

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

			System.out.println("ProductCofig ID :: " + productConfig.getId());

			System.out.println(
					"*************************************************************11*********************************************");
			productConfig = productConfigService.findProducConfigtByID(productConfig.getId());

			Product product = productService.findProductById(productConfig.getProduct().getId());// product

			System.out.println(
					"*************************************************************22*********************************************");

			System.out.println("yyyy" + imageMultipart.length);

			if (imageMultipart.length > product.getMaximumQuantity()) {

				model.addAttribute("notEnoughStock", true);

				return "forward:/productDetail?id=" + product.getId();
			}
			try {
				List<CartItem> cartItemList = user.getShoppingCart().getCartItemList();
				System.out.println();
				if(cartItemList.size()!=0) {
				for (CartItem cartItem : cartItemList) {

					if (cartItem.getProduct().equals(product)) {
						String path = null;
						int count = cartItem.getQty();
						for (MultipartFile multipartFile : imageMultipart) {

							byte[] bytes = multipartFile.getBytes();
							count++;
							String name = user.getShoppingCart().getId() + "_" + product.getId() + "_" + count
									+ "_.png";

							File file = new File("src/main/resources/static/img/user/userproductImage/" + name);
							FileOutputStream out = new FileOutputStream(file);
							BufferedOutputStream strem = new BufferedOutputStream(out);
							strem.write(bytes);
							path = "/img/user/userproductImage/" + name;
							strem.close();
						}
						System.out.println(
								"*************************************************************33*********************************************");
						System.out.println("productConfig::::" + productConfig);

						CartItem cartItems = cartItemService.addProductToCartItem(path, productConfig, product, user,
								count);

						model.addAttribute("addBookSuccess", true);

						return "forward:/productDetail?id=" + product.getId();
					} else {
						String newpath = null;
						int newcount = 0;

						for (MultipartFile multipartFile : imageMultipart) {

							byte[] bytes = multipartFile.getBytes();
							newcount++;
							String name = user.getShoppingCart().getId() + "_" + product.getId() + "_" + newcount
									+ "_.png";

							File file = new File("src/main/resources/static/img/user/userproductImage/" + name);
							FileOutputStream out = new FileOutputStream(file);
							BufferedOutputStream strem = new BufferedOutputStream(out);
							strem.write(bytes);
							newpath = "/img/user/userproductImage/" + name;
							strem.close();
						}
						System.out.println(
								"*************************************************************33*********************************************");
						System.out.println("productConfig::::" + productConfig);

						CartItem cartItems = cartItemService.addProductToCartItem(newpath, productConfig, product, user,
								newcount);

						model.addAttribute("addBookSuccess", true);

						return "forward:/productDetail?id=" + product.getId();
					}

				}
			}
				else {
					String newpath = null;
					int newcount = 0;

					for (MultipartFile multipartFile : imageMultipart) {

						byte[] bytes = multipartFile.getBytes();
						newcount++;
						String name = user.getShoppingCart().getId() + "_" + product.getId() + "_" + newcount
								+ "_.png";

						File file = new File("src/main/resources/static/img/user/userproductImage/" + name);
						FileOutputStream out = new FileOutputStream(file);
						BufferedOutputStream strem = new BufferedOutputStream(out);
						strem.write(bytes);
						newpath = "/img/user/userproductImage/" + name;
						strem.close();
					}
					System.out.println(
							"*************************************************************33*********************************************");
					System.out.println("productConfig::::" + productConfig);

					CartItem cartItems = cartItemService.addProductToCartItem(newpath, productConfig, product, user,
							newcount);

					model.addAttribute("addBookSuccess", true);

					return "forward:/productDetail?id=" + product.getId();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
		return qty;
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
	public String removeItem(@RequestParam("id") Long id, Authentication authentication, Principal principal) {
		System.out.println("id  ::  " + id);
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
				Product product = cartItem.getProduct();
				String name = user.getId() + "_" + product.getId() + ".png";
				Files.delete(Paths.get("src/main/resources/static/img/user/userproductImage/" + name));
				cartItemService.removeCartItem(cartItemService.findById(id));

				return "forward:/cart";
			}
		} catch (Exception e) {
			System.out.println("id  null In ShoppingCartController   Method:: removeItem  ::  " + e.getMessage());
			return "badRequestPage";
		}
	}

}
