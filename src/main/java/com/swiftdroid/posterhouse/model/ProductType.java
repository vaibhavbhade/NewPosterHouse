package com.swiftdroid.posterhouse.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="mst_product_type")
public class ProductType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ptc")
	private String productTypeCode;
	
	@Column(name = "ptn")
	private String productTypeName;
	
	
	@Column(name = "ptd")
	private String productTypeDiscription;
	
	@Column(name = "IA")
	private boolean isActive=true;
	
	@Column(name = "CD")
	@CreationTimestamp
	private Date cretedDate;

	@Column(name = "CB", updatable = false)
	private String cretedBy;

	@Column(name = "MD")
	@UpdateTimestamp
	private Date modifiedDate;
	
	@Column(name = "MB")
	private String modifiedBy;

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "productType", cascade = CascadeType.ALL)
	  private Set<Product> products = new HashSet<Product>();


	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getProductTypeCode() {
		return productTypeCode;
	}


	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}


	public String getProductTypeName() {
		return productTypeName;
	}


	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}


	public String getProductTypeDiscription() {
		return productTypeDiscription;
	}


	public void setProductTypeDiscription(String productTypeDiscription) {
		this.productTypeDiscription = productTypeDiscription;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Date getCretedDate() {
		return cretedDate;
	}


	public void setCretedDate(Date cretedDate) {
		this.cretedDate = cretedDate;
	}


	public String getCretedBy() {
		return cretedBy;
	}


	public void setCretedBy(String cretedBy) {
		this.cretedBy = cretedBy;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}


	 
	
	
	
	
	
}
