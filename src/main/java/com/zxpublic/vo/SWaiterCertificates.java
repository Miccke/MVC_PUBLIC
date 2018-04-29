package com.zxpublic.vo;


/**
 * 证件类型类
 * @author penggl
 * @date   2017年8月12日10:53:11
 */
public class SWaiterCertificates extends Page{
	
	//ID，主键
    private Long id;
    //证件名称
    private String cardName;
    //备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}