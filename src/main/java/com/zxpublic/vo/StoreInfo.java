package com.zxpublic.vo;

import java.util.Date;

public class StoreInfo {
	private Integer storeId;
	// 店名
	private String storeName;
	// 商家图片
	private String storeUrl;
	//联系电话
    private Long storePhone;
    //商家地址
    private String storeAddress;
	// 好评情况
	private Integer reviewStar;
	// 月销量
	private Integer salesVolume;
	// 起送价
	private Integer minimum;
	// 配送费
	private Integer deliveryCost;
	// 商家法人
	private String legalPerson;
	//用户ID 关联user表
	private Integer uid;
	//经度
    private String longitudex;
    //纬度
    private String latitudey;
    //空值字段用于返回值接收----距离
    private Double distance;
    //营业开始时间
    private Date openingStart;
    //营业结束时间
    private Date openingEnd;
    //显示标识 1表示显示，0 表示删除     默认1
  	private Integer codeNum;
  	
    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl == null ? null : storeUrl.trim();
    }

    public Long getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(Long storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress == null ? null : storeAddress.trim();
    }

	public Integer getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(Integer reviewStar) {
		this.reviewStar = reviewStar;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}

	public Integer getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Integer deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson == null ? null : legalPerson.trim();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

    public String getLongitudex() {
        return longitudex;
    }

    public void setLongitudex(String longitudex) {
        this.longitudex = longitudex == null ? null : longitudex.trim();
    }

    public String getLatitudey() {
        return latitudey;
    }

    public void setLatitudey(String latitudey) {
        this.latitudey = latitudey == null ? null : latitudey.trim();
    }
    
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

	public Date getOpeningStart() {
        return openingStart;
    }

    public void setOpeningStart(Date openingStart) {
        this.openingStart = openingStart;
    }

    public Date getOpeningEnd() {
        return openingEnd;
    }

    public void setOpeningEnd(Date openingEnd) {
        this.openingEnd = openingEnd;
    }
    
    public Integer getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Integer codeNum) {
		this.codeNum = codeNum;
	}
}
