package com.swiftdroid.posterhouse.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.swiftdroid.posterhouse.model.ProductType;

public interface CategoryRepository extends CrudRepository<ProductType, Long>
{
	
	
}
