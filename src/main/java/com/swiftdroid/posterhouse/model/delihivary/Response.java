package com.swiftdroid.posterhouse.model.delihivary;



import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("cash_pickups_count")
    private String cashPickupsCount;
	
	@JsonProperty("cod_count")
    private String codCount;
	
	@JsonProperty("success")
    private boolean success;
	
	@JsonProperty("package_count")
    private String packageCount;
	
	@JsonProperty("upload_wbn")
    private String uploadWbn;
	
	@JsonProperty("replacement_count")
    private String replacementCount;
	
	@JsonProperty("cod_amount")
    private String codAmount;
	
	@JsonProperty("prepaid_count")
    private String prepaidCount;
	
	@JsonProperty("rmk")
    private String rmk;
	
	@JsonProperty("pickups_count")
    private String pickupsCount;

	@JsonProperty("packages")
    private Packages[] packages;
    
    @JsonProperty("cash_pickups")
    private String cashPickups;
    
    

	@Override
	public String toString() {
		return "Response [cashPickupsCount=" + cashPickupsCount + ", codCount=" + codCount + ", success=" + success
				+ ", packageCount=" + packageCount + ", uploadWbn=" + uploadWbn + ", replacementCount="
				+ replacementCount + ", codAmount=" + codAmount + ", prepaidCount=" + prepaidCount + ", rmk=" + rmk
				+ ", pickupsCount=" + pickupsCount + ", packages=" + Arrays.toString(packages) + ", cashPickups="
				+ cashPickups + "]";
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String cashPickupsCount, String codCount, boolean success, String packageCount, String uploadWbn,
			String replacementCount, String codAmount, String prepaidCount, String rmk, String pickupsCount,
			Packages[] packages, String cashPickups) {
		super();
		this.cashPickupsCount = cashPickupsCount;
		this.codCount = codCount;
		this.success = success;
		this.packageCount = packageCount;
		this.uploadWbn = uploadWbn;
		this.replacementCount = replacementCount;
		this.codAmount = codAmount;
		this.prepaidCount = prepaidCount;
		this.rmk = rmk;
		this.pickupsCount = pickupsCount;
		this.packages = packages;
		this.cashPickups = cashPickups;
	}

	public String getCashPickupsCount() {
		return cashPickupsCount;
	}

	public void setCashPickupsCount(String cashPickupsCount) {
		this.cashPickupsCount = cashPickupsCount;
	}

	public String getCodCount() {
		return codCount;
	}

	public void setCodCount(String codCount) {
		this.codCount = codCount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getPackageCount() {
		return packageCount;
	}

	public void setPackageCount(String packageCount) {
		this.packageCount = packageCount;
	}

	public String getUploadWbn() {
		return uploadWbn;
	}

	public void setUploadWbn(String uploadWbn) {
		this.uploadWbn = uploadWbn;
	}

	public String getReplacementCount() {
		return replacementCount;
	}

	public void setReplacementCount(String replacementCount) {
		this.replacementCount = replacementCount;
	}

	public String getCodAmount() {
		return codAmount;
	}

	public void setCodAmount(String codAmount) {
		this.codAmount = codAmount;
	}

	public String getPrepaidCount() {
		return prepaidCount;
	}

	public void setPrepaidCount(String prepaidCount) {
		this.prepaidCount = prepaidCount;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getPickupsCount() {
		return pickupsCount;
	}

	public void setPickupsCount(String pickupsCount) {
		this.pickupsCount = pickupsCount;
	}

	public Packages[] getPackages() {
		return packages;
	}

	public void setPackages(Packages[] packages) {
		this.packages = packages;
	}

	public String getCashPickups() {
		return cashPickups;
	}

	public void setCashPickups(String cashPickups) {
		this.cashPickups = cashPickups;
	}

    
}
