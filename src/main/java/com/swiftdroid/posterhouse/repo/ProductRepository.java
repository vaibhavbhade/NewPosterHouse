package com.swiftdroid.posterhouse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductType;

public interface ProductRepository extends CrudRepository<Product, Long> {
	
public List<Product> findByproductType(ProductType productType);
@Query("select count(*) from Product where category_id=:key")
public long productCount(@Param("key")long id);

public List<Product> findFirst4ByproductType(ProductType productType);
public  List<Product> findByproductNameContaining(String title);


}
