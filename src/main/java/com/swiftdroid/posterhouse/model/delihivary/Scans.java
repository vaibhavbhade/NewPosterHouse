package com.swiftdroid.posterhouse.model.delihivary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Scans {

	@JsonProperty("ScanDetail")
	private ScanDetail scanDetail;

	@Override
	public String toString() {
		return "Scans [scanDetail=" + scanDetail + "]";
	}

	public ScanDetail getScanDetail() {
		return scanDetail;
	}

	public void setScanDetail(ScanDetail scanDetail) {
		this.scanDetail = scanDetail;
	}

}