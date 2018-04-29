package com.zxpublic.vo;

public class DishInfo extends Page {
	private Integer dishId;
	//��Ʒ����ID
	private Integer dishtypeId;
	//�̼�ID
	private Integer storeId;
	//��Ʒ����
	private String dishName;
	//��Ʒ���
	private String dishSpecification;
	//��������
	private Integer dishMonthsales;
	//���ۣ�Ԫ��
	private Float dishPrice;
	//��Ʒ����
	private int dishStar;
	// ��ƷͼƬID
	private String dishUrl;
	//��Ʒ����
	private String dishtype;
	//�̼���
	private String storeName;
	//��ʾ��ʶ 1��ʾ��ʾ��0 ��ʾɾ��     Ĭ��1
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
