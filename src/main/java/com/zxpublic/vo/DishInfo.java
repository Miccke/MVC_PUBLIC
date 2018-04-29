package com.zxpublic.vo;

public class DishInfo extends Page {
	private Integer dishId;
	//菜品类型ID
	private Integer dishtypeId;
	//商家ID
	private Integer storeId;
	//菜品名称
	private String dishName;
	//菜品规格
	private String dishSpecification;
	//月销售量
	private Integer dishMonthsales;
	//单价（元）
	private Float dishPrice;
	//菜品评级
	private int dishStar;
	// 菜品图片ID
	private String dishUrl;
	//菜品类型
	private String dishtype;
	//商家名
	private String storeName;
	//显示标识 1表示显示，0 表示删除     默认1
	private Integer codeNum;
	
	
	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	public Integer getDishtypeId() {
		return dishtypeId;
	}

	public void setDishtypeId(Integer dishtypeId) {
		this.dishtypeId = dishtypeId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName == null ? null : dishName.trim();
	}

	public String getDishSpecification() {
		return dishSpecification;
	}

	public void setDishSpecification(String dishSpecification) {
		this.dishSpecification = dishSpecification == null ? null
				: dishSpecification.trim();
	}

	public Integer getDishMonthsales() {
		return dishMonthsales;
	}

	public void setDishMonthsales(Integer dishMonthsales) {
		this.dishMonthsales = dishMonthsales;
	}

	public Float getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(Float dishPrice) {
		this.dishPrice = dishPrice;
	}

	public int getDishStar() {
		return dishStar;
	}

	public void setDishStar(int dishStar) {
		this.dishStar = dishStar;
	}
	
	public String getDishUrl() {
		return dishUrl;
	}

	public void setDishUrl(String dishUrl) {
		this.dishUrl = dishUrl;
	}


	public String getDishtype() {
		return dishtype;
	}

	public void setDishtype(String dishtype) {
		this.dishtype = dishtype;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}

	@Override
	public String toString() {
		return "DishInfo [dishId=" + dishId + ", dishtypeId=" + dishtypeId
				+ ", storeId=" + storeId + ", dishName=" + dishName
				+ ", dishSpecification=" + dishSpecification
				+ ", dishMonthsales=" + dishMonthsales + ", dishPrice="
				+ dishPrice + ", dishStar=" + dishStar + ", dishUrl=" + dishUrl
				+ ", dishtype=" + dishtype + ", storeName=" + storeName
				+ ", codeNum=" + codeNum + "]";
	}
	
	

}
