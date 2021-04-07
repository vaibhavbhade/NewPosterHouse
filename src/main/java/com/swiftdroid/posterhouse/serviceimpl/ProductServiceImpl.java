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
		List<Product> activeproductList=new ArrayList<Product>();
		 List<Product> productList= (List<Product>) productRepository.findAll();
		 for (Product product : productList) {
				if (product.isStatus()) {
					activeproductList.add(product);
				}
			}
		return activeproductList;	}

	@Override
	public List<Product> getProductCategoryWise(ProductType productType) {
		List<Product> activeproductList=new ArrayList<Product>();
		 List<Product> productList= productRepository.findByproductType(productType);
		 for (Product product : productList) {
				if (product.isStatus()) {
					activeproductList.add(product);
				}
		 }
		return activeproductList;
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
		List<Product> activeproductList = new ArrayList<>();
		List<Product> productList = productRepository.findFirst4ByproductType(productType);
	
		for (Product product : productList) {
			if (product.isStatus()) {
				activeproductList.add(product);
			}
		}
		return activeproductList;
	}
		 

	public List<Product> blurrySearch(String title) {
		List<Product> productList = productRepository.findByproductNameContaining(title);
		List<Product> activeproductList = new ArrayList<>();

		for (Product product : productList) {
			if (product.isStatus()) {
				activeproductList.add(product);
			}
		}

		return activeproductList;
	}

}
