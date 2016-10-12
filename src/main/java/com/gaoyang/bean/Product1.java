package com.gaoyang.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Product1 extends Product{
	private String productName;
	private String productPicUrl;
	private String productNo;
	private String validityPerBegin;
	private String validityPerEnd;
	private String btnStatus;
	private String btnText;
	private String bakNo;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPicUrl() {
		return productPicUrl;
	}
	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getValidityPerBegin() {
		return validityPerBegin;
	}
	public void setValidityPerBegin(String validityPerBegin) {
		this.validityPerBegin = validityPerBegin;
	}
	public String getBtnStatus() {
		return btnStatus;
	}
	public void setBtnStatus(String btnStatus) {
		this.btnStatus = btnStatus;
	}
	public String getBtnText() {
		return btnText;
	}
	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}
	public String getValidityPerEnd() {
		return validityPerEnd;
	}
	public void setValidityPerEnd(String validityPerEnd) {
		this.validityPerEnd = validityPerEnd;
	}
	public String getBakNo() {
		return bakNo;
	}
	public void setBakNo(String bakNo) {
		this.bakNo = bakNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product1 product1 = (Product1) o;

		if (productName != null ? !productName.equals(product1.productName) : product1.productName != null)
			return false;
		if (productPicUrl != null ? !productPicUrl.equals(product1.productPicUrl) : product1.productPicUrl != null)
			return false;
		if (productNo != null ? !productNo.equals(product1.productNo) : product1.productNo != null) return false;
		if (validityPerBegin != null ? !validityPerBegin.equals(product1.validityPerBegin) : product1.validityPerBegin != null)
			return false;
		if (validityPerEnd != null ? !validityPerEnd.equals(product1.validityPerEnd) : product1.validityPerEnd != null)
			return false;
		if (btnStatus != null ? !btnStatus.equals(product1.btnStatus) : product1.btnStatus != null) return false;
		return btnText != null ? btnText.equals(product1.btnText) : product1.btnText == null;

	}

	@Override
	public int hashCode() {
		int result = productName != null ? productName.hashCode() : 0;
		result = 31 * result + (productPicUrl != null ? productPicUrl.hashCode() : 0);
		result = 31 * result + (productNo != null ? productNo.hashCode() : 0);
		result = 31 * result + (validityPerBegin != null ? validityPerBegin.hashCode() : 0);
		result = 31 * result + (validityPerEnd != null ? validityPerEnd.hashCode() : 0);
		result = 31 * result + (btnStatus != null ? btnStatus.hashCode() : 0);
		result = 31 * result + (btnText != null ? btnText.hashCode() : 0);
		return result;
	}
}