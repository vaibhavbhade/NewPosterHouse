package com.swiftdroid.posterhouse.service;

import java.util.List;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;

public interface ProductConfigService {
	public List<ProductConfig> findProductConfigByProductId(Product product);

	public ProductConfig findProducConfigtByID(long id); 
	

	List<ProductConfig> findProductSize(long productId);

    ProductConfig findProductConfigByProductAndSize(Product product, String size); 

}
