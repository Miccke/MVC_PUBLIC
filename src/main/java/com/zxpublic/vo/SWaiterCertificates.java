package com.zxpublic.vo;


/**
 * ֤��������
 * @author penggl
 * @date   2017��8��12��10:53:11
 */
public class SWaiterCertificates extends Page{
	
	//ID������
    private Long id;
    //֤������
    private String cardName;
    //��ע
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