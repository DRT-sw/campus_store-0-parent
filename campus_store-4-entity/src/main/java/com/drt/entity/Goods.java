package com.drt.entity;

import java.math.BigDecimal;

public class Goods {
	private Integer id;

	private String goodsName;

	private BigDecimal goodsPrice;

	private Integer goodsSales;

	private Integer goodsStock;

	private String goodsImgPath;

	private String goodsCategory;

	public Goods() {
		super();
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice + ", goodsSales="
				+ goodsSales + ", goodsStock=" + goodsStock + ", goodsImgPath=" + goodsImgPath + ", goodsCategory="
				+ goodsCategory + "]";
	}

	public Goods(Integer id, String goodsName, BigDecimal goodsPrice, Integer goodsSales, Integer goodsStock,
			String goodsImgPath, String goodsCategory) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsSales = goodsSales;
		this.goodsStock = goodsStock;
		this.goodsImgPath = goodsImgPath;
		this.goodsCategory = goodsCategory;
	}

	public String getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(String goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName == null ? null : goodsName.trim();
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public Integer getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(Integer goodsSales) {
		this.goodsSales = goodsSales;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public String getGoodsImgPath() {
		return goodsImgPath;
	}

	public void setGoodsImgPath(String goodsImgPath) {
		this.goodsImgPath = goodsImgPath == null ? null : goodsImgPath.trim();
	}
}