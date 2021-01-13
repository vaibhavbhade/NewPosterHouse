package com.swiftdroid.posterhouse.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="mst_product_image")
public class ProductImage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(name="mainImage")
	private String mainImageUrl;
	
	@Column(name="extraImage1")
	private String extramageUrl1;
	
	@Column(name="extraImage2")
	private String extraImageUrl2;
	
	@Column(name="extraImage3")
	private String extraImageUrl3;
	
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

		public ProductImage() {
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

		public String getMainImageUrl() {
			return mainImageUrl;
		}

		public void setMainImageUrl(String mainImageUrl) {
			this.mainImageUrl = mainImageUrl;
		}

		public String getExtramageUrl1() {
			return extramageUrl1;
		}

		public void setExtramageUrl1(String extramageUrl1) {
			this.extramageUrl1 = extramageUrl1;
		}

		

		public String getExtraImageUrl2() {
			return extraImageUrl2;
		}

		public void setExtraImageUrl2(String extraImageUrl2) {
			this.extraImageUrl2 = extraImageUrl2;
		}

		public String getExtraImageUrl3() {
			return extraImageUrl3;
		}

		public void setExtraImageUrl3(String extraImageUrl3) {
			this.extraImageUrl3 = extraImageUrl3;
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
		
		
		

}
