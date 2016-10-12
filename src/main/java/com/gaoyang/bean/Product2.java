package com.gaoyang.bean;

public class Product2 extends Product
{
  private String name;
  private String picUrl;
  private String productNo;
  private String saleBeginTime;
  private String saleEndTime;
  private String showBeginTime;
  private String showEndTime;
  private String showStatus;
  private String showWeekday;
  private String stock;
  private String type;

  public String getName()
  {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPicUrl() {
    return this.picUrl;
  }
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }
  public String getProductNo() {
    return this.productNo;
  }
  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }
  public String getSaleBeginTime() {
    return this.saleBeginTime;
  }
  public void setSaleBeginTime(String saleBeginTime) {
    this.saleBeginTime = saleBeginTime;
  }
  public String getSaleEndTime() {
    return this.saleEndTime;
  }
  public void setSaleEndTime(String saleEndTime) {
    this.saleEndTime = saleEndTime;
  }
  public String getShowBeginTime() {
    return this.showBeginTime;
  }
  public void setShowBeginTime(String showBeginTime) {
    this.showBeginTime = showBeginTime;
  }
  public String getShowEndTime() {
    return this.showEndTime;
  }
  public void setShowEndTime(String showEndTime) {
    this.showEndTime = showEndTime;
  }
  public String getShowStatus() {
    return this.showStatus;
  }
  public void setShowStatus(String showStatus) {
    this.showStatus = showStatus;
  }
  public String getShowWeekday() {
    return this.showWeekday;
  }
  public void setShowWeekday(String showWeekday) {
    this.showWeekday = showWeekday;
  }
  public String getStock() {
    return this.stock;
  }
  public void setStock(String stock) {
    this.stock = stock;
  }
  public String getType() {
    return this.type;
  }
  public void setType(String type) {
    this.type = type;
  }
}