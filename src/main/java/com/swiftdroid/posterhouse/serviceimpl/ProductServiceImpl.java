package com.swiftdroid.posterhouse.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.Product;
import com.swiftdroid.posterhouse.model.ProductType;
import com.swiftdroid.posterhouse.repo.ProductRepository;
import com.swiftdroid.posterhouse.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public List<Product> getProductCategoryWise(ProductType productType) {
		// TODO Auto-generated method stub
		return productRepository.findByproductType(productType);
	}

	@Override
	public Product findProductById(Long Id) {
		// TODO Auto-generated method stub
		return productRepository.findById(Id).orElse(null);
	}

	@Override
	public long countProductCatIdWise(long id) {
		// TODO Auto-generated method stub
		return productRepository.productCount(id);
	}

	@Override
	public List<Product> findTopFourProductCategoryWise(ProductType productType) {
		// TODO Auto-generated method stub
		return productRepository.findFirst4ByproductType(productType);
	}

	public List<Product> blurrySearch(String title) {
		List<Product> productList = productRepository.findByproductNameContaining(title);
		List<Product> activeproductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isEnable()) {
				activeproductList.add(product);
			}
		}

		return activeproductList;
	}

}
