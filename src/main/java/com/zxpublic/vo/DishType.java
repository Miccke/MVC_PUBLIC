package com.zxpublic.vo;

public class DishType extends Page{
	private Integer dishtypeId;
	//��Ʒ����
	private String dishtype;
	//�̼�ID
	private Integer storeId;
	//�̼�����
	private String storeName;
	//��ʾ��ʶ 1��ʾ��ʾ��0 ��ʾɾ��     Ĭ��1
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
