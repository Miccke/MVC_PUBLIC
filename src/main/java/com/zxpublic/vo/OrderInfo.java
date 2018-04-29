package com.zxpublic.vo;

import java.util.Date;

public class OrderInfo extends Page {
	private Integer orderId;

	private String orderNum;

	private String content;

	private String realname;

	private Long phonenum;

	private Date ordertime;
	
	private Date sendtime;

	private Integer storeId;

	private String storeName;
	
	private Integer deliveryCost;

	private Double amount;

	private Integer orderstate;

	private String address;

	private String num;
	private Date finishtime;
	private Integer orderScore;
	private Date startTimes;
	private Date endTimes;
	private Integer IntervalNumber;
	private Integer allNumber;
	private String openid;
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum == null ? null : orderNum.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public Long getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(Long phonenum) {
		this.phonenum = phonenum;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num == null ? null : num.trim();
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid == null ? null : openid.trim();
	}
	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", orderNum=" + orderNum
				+ ", content=" + content + ", realname=" + realname
				+ ", phonenum=" + phonenum + ", ordertime=" + ordertime
				+ ", sendtime=" + sendtime + ", storeId=" + storeId
				+ ", storeName=" + storeName + ", amount=" + amount
				+ ", orderstate=" + orderstate + ", address=" + address
				+ ", num=" + num + ", finishtime=" + finishtime
				+ ", orderScore=" + orderScore + ", openid=" + openid + "]";
	}


	public Integer getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Integer deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public Date getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(Date startTimes) {
		this.startTimes = startTimes;
	}

	public Date getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(Date endTimes) {
		this.endTimes = endTimes;
	}

	public Integer getIntervalNumber() {
		return IntervalNumber;
	}

	public void setIntervalNumber(Integer intervalNumber) {
		IntervalNumber = intervalNumber;
	}

	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}

	public Integer getOrderScore() {
		return orderScore;
	}

	public void setOrderScore(Integer orderScore) {
		this.orderScore = orderScore;
	}
}
