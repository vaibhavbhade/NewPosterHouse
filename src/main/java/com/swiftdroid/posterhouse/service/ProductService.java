package com.swiftdroid.posterhouse.service;

import java.util.List;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductType;

public interface ProductService {

	public List<Product> getAllProduct();
	List<Product> getProductCategoryWise(ProductType productType); 	
	public Product findProductById(Long Id);

	public long countProductCatIdWise(long id);
	
	public List<Product> findTopFourProductCategoryWise(ProductType productType);
	public List<Product> blurrySearch(String keyword);
}
