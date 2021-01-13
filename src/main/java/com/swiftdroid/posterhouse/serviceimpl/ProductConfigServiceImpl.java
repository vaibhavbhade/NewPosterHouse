package com.swiftdroid.posterhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductConfig;
import com.swiftdroid.posterhouse.repo.ProductConfigRepository;
import com.swiftdroid.posterhouse.service.ProductConfigService;

@Service
public class ProductConfigServiceImpl  implements ProductConfigService{

	@Autowired
	private ProductConfigRepository productConfigRepository;
	
	
	@Override
	public List<ProductConfig> findProductConfigByProductId(Product product) {
		// TODO Auto-generated method stub
		return productConfigRepository.findByproduct(product);
	}


	@Override
	public ProductConfig findProducConfigtByID(long id) {
		// TODO Auto-generated method stub
		return productConfigRepository.findById(id).orElse(null);
	}


	@Override
	public List<ProductConfig> findProductSize(long productId) {
		return null;
		// TODO Auto-generated method stub
		//return productConfigRepository.findByproduct(productId)
	}


	@Override
	public ProductConfig findProductConfigByProductAndSize(Product product, String size) {
		// TODO Auto-generated method stub
		return productConfigRepository.findProductConfigByProductAndsize(product, size);
	}

}
