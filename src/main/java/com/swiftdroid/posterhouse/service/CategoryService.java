package com.swiftdroid.posterhouse.service;

import java.util.List;

import com.swiftdroid.posterhouse.model.ProductType;

public interface CategoryService {

	public ProductType findCategoryById(Long id);
	List<ProductType> findAllProductType();
	
}
