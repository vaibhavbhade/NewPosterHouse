package com.swiftdroid.posterhouse.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swiftdroid.posterhouse.config.CustomOAuth2User;
import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.service.OrderService;
import com.swiftdroid.posterhouse.service.UserService;

@Controller
public class OrderController {
	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("finally")
	@RequestMapping("/userOrder")
	public String orderPage(Model model, Authentication authentication, Principal principal) {

		User user = null;
		try {
			CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
			String email = oAuth2User.getEmail();
			System.out.println("eeeeeeeeeeeeeeee" + email);
			user = userService.findByEmail(email);
		} catch (Exception e) {
			user = userService.findByUsername(principal.getName());
		} finally {
			try {
				List<Order> orderList = user.getOrderList();
				model.addAttribute("orderList", orderList);
				model.addAttribute("user", user);
				return "orderPage";
			} catch (Exception e) {
				System.out.println("order Id is Null In OrderController :: Methd :: orderPage  /userOrder ");
				return "badRequestPage";
			}
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/orderDetail")
	public String orderPage(@RequestParam("id") Long orderId, Model model, Authentication authentication,
			Principal principal) {

		if (orderId == null) {
			System.out.println("order Id is Null In OrderController :: Methd :: orderPage ");
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

			try {
				Order order = orderService.findOrderById(orderId);

				if (order.getUser().getId().longValue() != user.getId().longValue()) {
					return "badRequestPage";
				}

				model.addAttribute("user", user);
				model.addAttribute("ShippingAddress", order.getShippingAddress());
				model.addAttribute("BillingAddress", order.getBillingAddress());
				model.addAttribute("order", order);
				model.addAttribute("estimatedDeliveryDate", order.getEstimateDate());
				model.addAttribute("cartItemList", order.getCartItemList());

				return "orderDetails";
			} catch (Exception e) {
				System.out.println("order Id is Null In OrderController :: Methd :: orderPage  /orderDetail ");
				return "badRequestPage";
			}

		}
	}

	
	  @ResponseBody
	  
	  @GetMapping("/downloadImage") public ResponseEntity<Object>
	  downloadImage(@PathParam("orderId") Long orderId) throws IOException {
	  System.out.println(
	  "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +
	  orderId);
	  
	  Order order = orderService.findOrderById(orderId);
	  
	  List<CartItem> CartItemList = order.getCartItemList();
	
	  User user = order.getUser();
	  
	  for (CartItem cartItem : CartItemList) {
	  
	  Product product = cartItem.getProduct();
	  
	  String fileName = user.getId() + "_" + product.getId() + ".png";
	  
	  String photoName = "C:\\java\\POSTERHOUSE\\src\\main\\resources\\static\\img\\user\\userproductImage\\" +
	  fileName;
	  
	  File file = new File(photoName);
	  System.out.println("222222222222222222222222222222222222");
	  
	  InputStreamResource resource = new InputStreamResource(new
	  FileInputStream(file));
	  
	  HttpHeaders headers = new HttpHeaders(); headers.add("Content-Disposition",
	  String.format("attachment; filename=\"%s\"", file.getName()));
	  headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	  headers.add("Pragma", "no-cache"); headers.add("Expires", "0");
	   
	   ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
	  .contentType(MediaType.parseMediaType("application/octet-stream")).body(
	  resource);
	  return responseEntity; 
	   }
	
	return null; 
	
	  
	  
	  }
	 
	}
