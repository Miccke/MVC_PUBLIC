package com.zxpublic.vo;

import java.util.Date;

public class StoreInfo {
	private Integer storeId;
	// ����
	private String storeName;
	// �̼�ͼƬ
	private String storeUrl;
	//��ϵ�绰
    private Long storePhone;
    //�̼ҵ�ַ
    private String storeAddress;
	// �������
	private Integer reviewStar;
	// ������
	private Integer salesVolume;
	// ���ͼ�
	private Integer minimum;
	// ���ͷ�
	private Integer deliveryCost;
	// �̼ҷ���
	private String legalPerson;
	//�û�ID ����user��
	private Integer uid;
	//����
    private String longitudex;
    //γ��
    private String latitudey;
    //��ֵ�ֶ����ڷ���ֵ����----����
    private Double distance;
    //Ӫҵ��ʼʱ��
    private Date openingStart;
    //Ӫҵ����ʱ��
    private Date openingEnd;
    //��ʾ��ʶ 1��ʾ��ʾ��0 ��ʾɾ��     Ĭ��1
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
