package com.swiftdroid.posterhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.ProductType;
import com.swiftdroid.posterhouse.repo.CategoryRepository;
import com.swiftdroid.posterhouse.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<ProductType> findAllProductType() {
		// TODO Auto-generated method stub
		return (List<ProductType>) categoryRepository.findAll();
	}

	@Override
	public ProductType findCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}

}
