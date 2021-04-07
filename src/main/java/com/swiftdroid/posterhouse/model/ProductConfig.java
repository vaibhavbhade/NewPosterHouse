package com.swiftdroid.posterhouse.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="ProductConfig")
@Table(name="mst_product_config")
public class ProductConfig  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
   @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="product_id")
	private Product product;
	
	@OneToMany(mappedBy = "productConfig")
	@JsonIgnore
	private List<ProductToCartItem> ProductToCartItemList;
	
	
	@Column(name = "PCC")
	private String productConfigCode;
	
	@Column(name = "PCN")
	private String productConfigName;
	
	
	@Column(name = "PCD" ,columnDefinition="text")
	private String productConfigDiscription;
	
	
	@Column(name = "sz")
	private String size;
	
	
	@Column(name = "WD")
	private int WIDTH;
	
	@Column(name = "HT")
	private int HEIGHT;
	
	@Column(name = "DT")
	private int DEPTH;
	
	@Column(name = "WT")
	private int weightOfProduct;
	
	@Column(name = "TW")
	private int totalWeight;
	
	
	@Column(name = "ppq")
	private double pricePerQty;
	
	@Column(name = "ppf")
	private int pricePerFace;
	
	@Column(name = "DR")
	private String durationToMake;
	
	@Column(name = "AV")
	private int availibility;

	@Column(name = "IA")
	private boolean status;
	
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

	public ProductConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductConfigCode() {
		return productConfigCode;
	}

	public void setProductConfigCode(String productConfigCode) {
		this.productConfigCode = productConfigCode;
	}

	public String getProductConfigName() {
		return productConfigName;
	}

	public void setProductConfigName(String productConfigName) {
		this.productConfigName = productConfigName;
	}

	public String getProductConfigDiscription() {
		return productConfigDiscription;
	}

	public void setProductConfigDiscription(String productConfigDiscription) {
		this.productConfigDiscription = productConfigDiscription;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public int getDEPTH() {
		return DEPTH;
	}

	public void setDEPTH(int dEPTH) {
		DEPTH = dEPTH;
	}

	public int getWeightOfProduct() {
		return weightOfProduct;
	}

	public void setWeightOfProduct(int weightOfProduct) {
		this.weightOfProduct = weightOfProduct;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public double getPricePerQty() {
		return pricePerQty;
	}

	public void setPricePerQty(double pricePerQty) {
		this.pricePerQty = pricePerQty;
	}

	public int getPricePerFace() {
		return pricePerFace;
	}

	public void setPricePerFace(int pricePerFace) {
		this.pricePerFace = pricePerFace;
	}

	public String getDurationToMake() {
		return durationToMake;
	}

	public void setDurationToMake(String durationToMake) {
		this.durationToMake = durationToMake;
	}

	public int getAvailibility() {
		return availibility;
	}

	public void setAvailibility(int availibility) {
		this.availibility = availibility;
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
	
	
	
	@JsonIgnore
	public List<ProductToCartItem> getProductToCartItemList() {
		return ProductToCartItemList;
	}

	public void setProductToCartItemList(List<ProductToCartItem> productToCartItemList) {
		ProductToCartItemList = productToCartItemList;
	}
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductConfig [id=" + id + ", product=" + product + ", productConfigCode=" + productConfigCode
				+ ", productConfigName=" + productConfigName + ", productConfigDiscription=" + productConfigDiscription
				+ ", size=" + size + ", WIDTH=" + WIDTH + ", HEIGHT=" + HEIGHT + ", DEPTH=" + DEPTH
				+ ", weightOfProduct=" + weightOfProduct + ", totalWeight=" + totalWeight + ", pricePerQty="
				+ pricePerQty + ", pricePerFace=" + pricePerFace + ", durationToMake=" + durationToMake
				+ ", availibility=" + availibility + ", isActive=" + status + ", cretedDate=" + cretedDate
				+ ", cretedBy=" + cretedBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
	
	
}
