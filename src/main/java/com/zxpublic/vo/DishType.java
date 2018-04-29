package com.zxpublic.vo;

public class DishType extends Page{
	private Integer dishtypeId;
	//菜品类型
	private String dishtype;
	//商家ID
	private Integer storeId;
	//商家名称
	private String storeName;
	//显示标识 1表示显示，0 表示删除     默认1
	private Integer codeNum;
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getDishtypeId() {
		return dishtypeId;
	}

	public void setDishtypeId(Integer dishtypeId) {
		this.dishtypeId = dishtypeId;
	}

	public String getDishtype() {
		return dishtype;
	}

	public void setDishtype(String dishtype) {
		this.dishtype = dishtype == null ? null : dishtype.trim();
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}
}
