package com.swiftdroid.posterhouse.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.PaytmChecksum;
import com.swiftdroid.posterhouse.config.CustomOAuth2User;
import com.swiftdroid.posterhouse.model.BillingAddress;
import com.swiftdroid.posterhouse.model.CartItem;
import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.PaytmDetails;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ShippingAddress;
import com.swiftdroid.posterhouse.model.ShoppingCart;
import com.swiftdroid.posterhouse.model.User;
import com.swiftdroid.posterhouse.model.UserBilling;
import com.swiftdroid.posterhouse.model.UserShipping;
import com.swiftdroid.posterhouse.service.BillingAddressService;
import com.swiftdroid.posterhouse.service.CartItemService;
import com.swiftdroid.posterhouse.service.OrderService;
import com.swiftdroid.posterhouse.service.ShippingAddressService;
import com.swiftdroid.posterhouse.service.ShoppingCartService;
import com.swiftdroid.posterhouse.service.UserService;
import com.swiftdroid.posterhouse.service.UserShippingService;
import com.swiftdroid.posterhouse.utility.INDConstants;
import com.swiftdroid.posterhouse.utility.MailConstructor;

@Controller
public class CheckOutController {

	@Autowired
	private Environment env;

	@Autowired
	private PaytmDetails paytmDetails;

	private ShippingAddress shippingAddress = new ShippingAddress();
	private BillingAddress billingAddress = new BillingAddress();
	private Order Grandorder = new Order();

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailConstructor mailConstructor;

	@Autowired
	private ShippingAddressService shippingAddressService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private BillingAddressService billingAddressService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserShippingService userShippingService;

	@SuppressWarnings("finally")
	@RequestMapping("/checkout")
	public String checkoutPage(@RequestParam("id") Long cartId,
			@RequestParam(value = "missingRequiredField", required = false) boolean missingRequiredField, Model model,

			Principal principal, Authentication authentication) {

		if (cartId == null) {
			System.out.println("cartId IS CheckOutController   Method:: checkoutPage  ::  ");
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

				System.out.println("**********  ******   " + cartId + " == " + user.getShoppingCart().getId());

				if (cartId.longValue() != user.getShoppingCart().getId().longValue()) {
					return "badRequestPage";
				}
				
				if(Grandorder.getId() != null) {
					System.out.println("delete by id   ::::  "+Grandorder.getId());
					orderService.deleteOrderById(Grandorder);
				}
				
				System.out.println("######################################## :::::  "+Grandorder.getId());
				List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

				if (cartItemList.size() == 0) {
					model.addAttribute("emptyCart", true);
					return "forward:/cart";
				}

				for (CartItem cartItem : cartItemList) {
					if (cartItem.getProduct().getMaximumQuantity() < cartItem.getQty()) {
						model.addAttribute("notEnoughStock", true);
						return "forward:/cart";
					}
				}

				List<UserShipping> userShippingList = user.getUserShippingList();
				List<UserBilling> UserBillingtList = user.getUserBilingList();

				model.addAttribute("userShippingList", userShippingList);
				model.addAttribute("UserBillingtList", UserBillingtList);

				if (UserBillingtList.size() == 0) {
					model.addAttribute("UserBillingtList", true);
				} else {
					model.addAttribute("UserBillingtList", false);
				}

				if (userShippingList.size() == 0) {
					model.addAttribute("emptyShippingList", true);
				} else {
					model.addAttribute("emptyShippingList", false);

					model.addAttribute("notemptyShippingList", true);
				}

				ShoppingCart shoppingCart = user.getShoppingCart();

				for (UserShipping userShipping : userShippingList) {
					if (userShipping.isUserShippingDefault()) {
						shippingAddressService.setByUserShipping(userShipping, shippingAddress);
					}
				}

				for (UserBilling userBilling : UserBillingtList) {
					if (userBilling.isUserBillingDefault()) {
						billingAddressService.setByUserBilling(userBilling, billingAddress);
					}

				}
				/*
				 * for (UserPayment userPayment : userPaymentList) {
				 * if(userPayment.isDefaultPayment()) {
				 * paymentService.setByUserPayment(userPayment, payment);
				 * billingAddressService.setByUserBilling(userPayment.getUserBilling(),
				 * billingAddress); } }
				 */

				model.addAttribute("shippingAddress", shippingAddress);
				model.addAttribute("billingAddress", billingAddress);
				model.addAttribute("cartItemList", cartItemList);
				model.addAttribute("shoppingCart", user.getShoppingCart());

				List<String> stateList = INDConstants.listOfIndianStateName;

				Collections.sort(stateList);

				model.addAttribute("stateList", stateList);

				model.addAttribute("classActiveShipping", true);

				if (missingRequiredField) {
					model.addAttribute("missingRequiredField", true);
				}

				return "checkout";
			}
		} catch (Exception e) {
			System.out.println("Exception IS CheckOutController   Method:: checkoutPage  ::  ");

			return "badRequestPage";
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView checkoutPost(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
			@ModelAttribute("billingAddress") BillingAddress billingAddress,
			@ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
			@ModelAttribute("shippingMethod") String shippingMethod, Principal principal, Model model,
			Authentication authentication) {

		try {
			ModelAndView modelAndView = new ModelAndView();
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

				model.addAttribute("cartItemList", cartItemList);

				if (billingSameAsShipping.equals("true")) {
					billingAddress.setBillingAddressName(shippingAddress.getShippingAddressName());
					billingAddress.setBillingAddressStreet1(shippingAddress.getShippingAddressStreet1());
					billingAddress.setBillingAddressStreet2(shippingAddress.getShippingAddressStreet2());
					billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
					billingAddress.setBillingAddressState(shippingAddress.getShippingAddressState());
					billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
					billingAddress.setBillingAddressZipcode(shippingAddress.getShippingAddressZipcode());
				}

				if (shippingAddress.getShippingAddressStreet1().isEmpty()
						|| shippingAddress.getShippingAddressCity().isEmpty()
						|| shippingAddress.getShippingAddressState().isEmpty()
						|| shippingAddress.getShippingAddressName().isEmpty()
						|| shippingAddress.getShippingAddressZipcode().isEmpty()
						|| billingAddress.getBillingAddressStreet1().isEmpty()
						|| billingAddress.getBillingAddressCity().isEmpty()
						|| billingAddress.getBillingAddressState().isEmpty()
						|| billingAddress.getBillingAddressName().isEmpty()
						|| billingAddress.getBillingAddressZipcode().isEmpty()) {
					modelAndView.setViewName(
							"redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true");
					return modelAndView;
				}

				Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, shippingMethod,
						user);
				Grandorder = order;

				String OrderId = "POSTERHOUSE" + order.getId();
				modelAndView.setViewName("redirect:" + paytmDetails.getPaytmUrl());
				TreeMap<String, String> parameters = new TreeMap<>();
				paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
				parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
				parameters.put("EMAIL", env.getProperty("paytm.email"));
				parameters.put("ORDER_ID", OrderId);// order.getFinalPrice().toString()
				parameters.put("TXN_AMOUNT",  "1");
				parameters.put("CUST_ID", order.getUser().getId().toString());
				String checkSum = getCheckSum(parameters);
				parameters.put("CHECKSUMHASH", checkSum);
				modelAndView.addAllObjects(parameters);

				return modelAndView;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception IS CheckOutController   Method:: checkoutPage  ::  " + e.getMessage());
			ModelAndView m = new ModelAndView("badRequestPage");
			return m;
		}

	}

	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
		return PaytmChecksum.verifySignature(parameters, paytmDetails.getMerchantKey(), paytmChecksum);
	}

	private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
		return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
	}

	@SuppressWarnings("finally")
	@RequestMapping("/setShippingAddress")
	public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal,
			Model model, Authentication authentication) {
		if (userShippingId == null) {
			System.out.println("userShippingId null  In CheckOutController   Method:: setShippingAddress  ::  ");
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
				UserShipping userShipping = userShippingService.findById(userShippingId);

				if (userShipping.getUser().getId().longValue() != user.getId().longValue()) {
					return "badRequestPage";
				} else {
					shippingAddressService.setByUserShipping(userShipping, shippingAddress);

					List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

					model.addAttribute("shippingAddress", shippingAddress);
					// model.addAttribute("payment", payment);
					model.addAttribute("billingAddress", billingAddress);
					model.addAttribute("cartItemList", cartItemList);
					model.addAttribute("shoppingCart", user.getShoppingCart());

					List<String> stateList = INDConstants.listOfIndianStateName;
					Collections.sort(stateList);
					model.addAttribute("stateList", stateList);

					List<UserShipping> userShippingList = user.getUserShippingList();
					// List<UserPayment> userPaymentList = user.getUserPaymentList();

					model.addAttribute("userShippingList", userShippingList);
					// model.addAttribute("userPaymentList", userPaymentList);

					model.addAttribute("shippingAddress", shippingAddress);

					model.addAttribute("classActiveShipping", true);

					/*
					 * if (userPaymentList.size() == 0) { model.addAttribute("emptyPaymentList",
					 * true); } else { model.addAttribute("emptyPaymentList", false); }
					 */

					model.addAttribute("emptyShippingList", false);

					return "checkout";
				}
			} catch (Exception e2) {
				System.out.println("userShippingId null  In CheckOutController   Method:: setShippingAddress  ::  "
						+ e2.getMessage());
				return "badRequestPage";
			}
		}
	}

	public void moveFile(Order order, User user) {

		List<CartItem> CartItemList = order.getCartItemList();

		for (CartItem cartItem : CartItemList) {

			Product product = cartItem.getProduct();
			String fileName = user.getId() + "_" + product.getId() + ".png";
			Path sourceFilePath = Paths.get("src/main/resources/static/img/user/userproductImage/" + fileName);
			String newfileName = user.getId() + "_" + product.getId() + "_" + order.getId() + "_"
					+ order.getOrderDate().getDate() + "-" + order.getOrderDate().getDay() + "_"
					+ order.getOrderDate().getYear() + ".png";
			Path targetFilePath = Paths.get("src/main/resources/static/img/user/userproductImage/" + newfileName);

			try {

				Files.move(sourceFilePath, targetFilePath);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@PostMapping(value = "/pgresponse")
	public String getResponseRedirect(HttpServletRequest request, Model model) {

		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum = "";
		for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
			if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
				paytmChecksum = requestParamsEntry.getValue()[0];
			} else {
				parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
			}
		}
		String result;
		User user = Grandorder.getUser();
		boolean isValideChecksum = false;
		System.out.println("RESULT : " + parameters.toString());
		try {
			isValideChecksum = validateCheckSum(parameters, paytmChecksum);
			if (isValideChecksum && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
					result = "Payment Successful";

					mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, Grandorder, Locale.ENGLISH));

					moveFile(Grandorder, user);

					shoppingCartService.clearShoppingCart(user.getShoppingCart());

					LocalDate today = LocalDate.now();
					LocalDate estimatedDeliveryDate;
					if (Grandorder.getShippingMethod().equals("groundShipping")) {
						estimatedDeliveryDate = today.plusDays(7);
					} else {
						estimatedDeliveryDate = today.plusDays(5);
					}

					model.addAttribute("ShippingAddress", Grandorder.getShippingAddress());
					model.addAttribute("BillingAddress", Grandorder.getBillingAddress());
					model.addAttribute("order", Grandorder);

					model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);
					parameters.remove("CHECKSUMHASH");
					model.addAttribute("parameters", parameters);
					return "orderConfirmationPage";

				} else {

					result = "Payment Failed";
					parameters.remove("CHECKSUMHASH");
					model.addAttribute("parameters", parameters);
					orderService.deleteOrderById(Grandorder);
					Grandorder=null;
System.out.println("###################################################################");
					return "redirect:/checkout?id=" + user.getShoppingCart().getId() + "&missingRequiredField=true";
				}
			} else {
				result = "Checksum mismatched";
				parameters.remove("CHECKSUMHASH");
				model.addAttribute("parameters", parameters);
				orderService.deleteOrderById(Grandorder);
				Grandorder=null;
				System.out.println("################################################################### kkkkkkkkk");

				return "redirect:/checkout?id=" + user.getShoppingCart().getId() + "&missingRequiredField=true";

			}
		} catch (Exception e) {
			result = e.toString();
		}
		model.addAttribute("result", result);
		parameters.remove("CHECKSUMHASH");
		model.addAttribute("parameters", parameters);
		System.out.println("################################################################### hhhhhhhhhhhhhh");

		return "redirect:/checkout?id=" + user.getShoppingCart().getId() + "&missingRequiredField=true";
	}
}
