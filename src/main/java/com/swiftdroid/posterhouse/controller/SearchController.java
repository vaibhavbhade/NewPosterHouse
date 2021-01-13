package com.swiftdroid.posterhouse.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductType;
import com.swiftdroid.posterhouse.service.CategoryService;
import com.swiftdroid.posterhouse.service.ProductService;

@Controller
public class SearchController {
	
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/searchProduct")
	public String searchProduct(@ModelAttribute("keyword") String keyword,
		Principal principal, Model model
		) {
	
	
	List<Product> productList = productService.blurrySearch(keyword);
	
	if (productList.isEmpty()) {
		model.addAttribute("emptyProductList", true);
		return "category";
	}
	Map<ProductType,Long> catCount = new HashMap<ProductType,Long>(); 

	List<ProductType> categoryList = categoryService.findAllProductType();
	for (ProductType productType : categoryList) {
		System.out.println(productType.getProductTypeName());
	}
	for (ProductType productType : categoryList) {

		System.out.println("count ::    " + productType.getProductTypeName() + "  :"
		+ productService.countProductCatIdWise(productType.getId()));
		
		catCount.put(productType, productService.countProductCatIdWise(productType.getId()));

	}
	model.addAttribute("productList", productList);
	model.addAttribute("catgoryCountList", catCount);
	return "category";
}
	
	
}
