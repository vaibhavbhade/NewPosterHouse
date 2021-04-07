package com.swiftdroid.posterhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swiftdroid.posterhouse.model.FeedBack;
import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.service.FeedBackService;
import com.swiftdroid.posterhouse.service.ProductService;

@Controller
public class FeedBackController {
	
@Autowired
private FeedBackService feedBackService;
	
@Autowired
private ProductService  productService;
	
@PostMapping("/feedback")	
public String saveUserFeedBack(@RequestParam("id") Long Id,@RequestParam("userName") String userName,@RequestParam("email") String email,@RequestParam("number") String number,@RequestParam("message") String message) {
	Product product=productService.findProductById(Id);
   System.out.println("Id : "+Id);

   FeedBack feedback=new FeedBack();
   feedback.setEmail(email);
   feedback.setFeedback(message);
   feedback.setPhoneNo(number);
   feedback.setUsername(userName);
   
   feedBackService.saveFeedback(feedback);
   
   
return "redirect:/productDetail?id="+product.getId()+"&feedback=true";
	

}
}
