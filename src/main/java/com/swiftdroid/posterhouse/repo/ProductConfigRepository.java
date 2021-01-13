package com.swiftdroid.posterhouse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;

public interface ProductConfigRepository extends CrudRepository<ProductConfig, Long> {

	List<ProductConfig> findByproduct(Product product);
//	List<ProductConfig>  findByproduct1(Product product);

	
	 @Query("from ProductConfig where product_id=:product And sz=:size") 
	 ProductConfig findProductConfigByProductAndsize(@Param("product")Product product,@Param("size")String size);
	
}
